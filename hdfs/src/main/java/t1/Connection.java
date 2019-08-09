package t1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Connection extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		
		Configuration conf = new Configuration();
		
		// local info
		FileSystem localSystem = FileSystem.getLocal(conf);
//		FileStatus[] fileList = localSystem.listStatus(new Path("D:\\hdfs"));
//		for(int i = 0; i < fileList.length; i++) {
//			System.out.println(fileList[i].getPath().getName());
//		}
		
		// hadoop info
		Configuration hadoopConf = new Configuration();
		hadoopConf.set("fs.defaultFS","hdfs://192.168.3.166:9000");
		FileSystem hadoopSystem = FileSystem.get(hadoopConf);
		if(hadoopSystem.exists(new Path("/output"))) {
			hadoopSystem.delete(new Path("/output"), true);
		}
//		FileSystem hadoopSystem = FileSystem.get(hadoopConf);
//		FileStatus[] fileList2 = hadoopSystem.listStatus(new Path("/input"));
//		for(int i = 0; i < fileList2.length; i++) {
//			System.out.println(fileList2[i].getPath().getName());
//		}
		
		try {
			Job job = Job.getInstance(hadoopConf,"test");
			job.setJarByClass(Connection.class);
			job.setMapperClass(JobMap.class);
			job.setReducerClass(JobReduce.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			job.setNumReduceTasks(1);
			
			FileInputFormat.addInputPath(job, new Path("/input"));
		    FileOutputFormat.setOutputPath(job, new Path("/output"));
		    job.waitForCompletion(true);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		FileStatus[] fileList2 = hadoopSystem.listStatus(new Path("/output"));
		PrintWriter pw = response.getWriter();
		
		
		if(fileList2.length > 1) {
			System.out.println(fileList2[1].getPath().getName());
			String path ="/output/"+fileList2[1].getPath().getName();
			FSDataInputStream fsis = hadoopSystem.open(new Path(path));
			int bytecode=0;
			while((bytecode = fsis.read())>0) {
				pw.write(bytecode);
			}
		}
//		pw.write("Hello hadoop");
		
		
		response.getWriter().write("Hello Hadoop!");
	}

}
