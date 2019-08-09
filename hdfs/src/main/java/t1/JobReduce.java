package t1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JobReduce extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		//결합할 값 변수 선언
		int sum=0;
		//입력받은 값을 반복문으로 합하기
		for(IntWritable v : value) {
			sum+=v.get();
		}
		//출력값 형변환 처리
		IntWritable result = new IntWritable();
		result.set(sum);
		
		//전체 결과값을 출력하기
		context.write(key, result);
	}

}
