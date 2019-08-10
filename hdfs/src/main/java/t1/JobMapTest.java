package t1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JobMapTest extends Mapper<LongWritable, Text, Text, IntWritable> {
	// map 출력값
	private final static IntWritable outputValue = new IntWritable(1);
	// map 출력키

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		Parser parser = new Parser(value);
		
		Text textKey = new Text();
		textKey.set(parser.getYear() + "," + parser.getMonth());
		context.write(textKey, outputValue);

	}
}
