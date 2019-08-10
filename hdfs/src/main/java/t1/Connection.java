package t1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		Configuration conf = new Configuration();
		// local info 로컬 정보가 필요한가?
		FileSystem localSystem = FileSystem.getLocal(conf);
		
		// hadoop info
		Configuration hadoopConf = new Configuration();
		//하둡측의 정보
		hadoopConf.set("fs.defaultFS","hdfs://192.168.29.169:9000");
		FileSystem hadoopSystem = FileSystem.get(hadoopConf);
		//이미 가져온 값이 있다면 처리하기
		if(hadoopSystem.exists(new Path("/output"))) {
			hadoopSystem.delete(new Path("/output"), true);
		}
		
		try {
			Job job = Job.getInstance(hadoopConf,"test");
			job.setJarByClass(Connection.class);
			//이해하고 수정해야 할 부분
			job.setMapperClass(JobMapTest.class);
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
		//output에 있는 값 가져오기
		FileStatus[] fileList2 = hadoopSystem.listStatus(new Path("/output"));
		PrintWriter pw = response.getWriter();
		
		//success 일 때?
		if(fileList2.length > 1) {
			System.out.println(fileList2[1].getPath().getName());
			String path ="/output/"+fileList2[1].getPath().getName();
			System.out.println("시작");
			
			
			//output/part- 파일 열어 스트림에 저장
			FSDataInputStream fsis = hadoopSystem.open(new Path(path));
//			int bytecode=0;
//			while((bytecode = fsis.read())>0) {
//				pw.write(bytecode);
//			}
			
			try {
				InputStream fsiss = hadoopSystem.open(new Path(path));
				BufferedReader br = new BufferedReader(new InputStreamReader(fsiss));
				String line = null;
				while ((line = br.readLine()) != null) {
				    //do something here
					String[] a=line.split("\\s");
					pw.write("key:"+a[0]+"<br>");
					pw.write("value:"+a[1]+"<br>");
//					for(String b : a) {
//						System.out.print(b);
//						pw.write(b);
//					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			/**/
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Hello Hadoop!");
	}

}
