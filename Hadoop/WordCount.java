import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
       StringTokenizer itr1 = new StringTokenizer(value.toString());
      StringTokenizer itr2 = new StringTokenizer(value.toString());

      if(itr2.hasMoreTokens()){
        itr2.nextToken();
      }
      while (itr1.hasMoreTokens()) {
        String aux = itr1.nextToken();
        if(itr1.hasMoreTokens()){
          aux+=itr1.nextToken();
		      System.out.println("1 "+aux);
          word.set(aux);
          context.write(word, one);
        }
        else{
          System.out.println("2 "+aux);
          word.set(aux);
          context.write(word, one);
        }
        if(itr2.hasMoreTokens()){
          String aux2 = itr2.nextToken();
          if(itr2.hasMoreTokens()){
            aux2+=itr2.nextToken();
            System.out.println("3 "+aux2);
            word.set(aux2);
            context.write(word, one);
          }else{
            System.out.println("4 "+aux2);
            word.set(aux2);
            context.write(word, one);
          }
        }

      }
  }
}
  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}