import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class GcCount {
    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable> {
        private final static Text gcWord    = new Text("GC");
        private final static Text totalWord = new Text("ACGT");
        private LongWritable gcCount    = new LongWritable();
        private LongWritable totalCount = new LongWritable(); 
 
        public void map(LongWritable key, Text value, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {
            String line = value.toString();
            long count = 0L;
            for (int i = 0; i < line.length(); ++i) {
	    if (line.charAt(i) == 'C' || line.charAt(i) == 'G') {
		    ++count;
		}
            }
          
            gcCount.set(count);
            totalCount.set(line.length()); 
            output.collect(gcWord, gcCount);
            output.collect(totalWord, totalCount);
        }
    }
	
    public static class Reduce extends MapReduceBase implements Reducer<Text, LongWritable, Text, LongWritable> {
        public void reduce(Text key, Iterator<LongWritable> values, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException { 
            long sum = 0L;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            output.collect(key, new LongWritable(sum));
        }
    }
	
    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(GcCount.class);
        conf.setJobName("wordcount");
	    
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(LongWritable.class);
	    
        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Reduce.class);
        conf.setReducerClass(Reduce.class);
	    
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
	    
        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	    
        JobClient.runJob(conf);
    }
}
