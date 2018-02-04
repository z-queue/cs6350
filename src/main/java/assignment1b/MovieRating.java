package assignment1b;

import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;


public class MovieRating extends Configured implements Tool {
    private static final Logger LOG = Logger.getLogger(MovieRating.class);
    
    public static void main(String[] args) throws Exception {
      int res = ToolRunner.run(new MovieRating(), args);
      System.exit(res);
    }

    public int run(String[] args) throws Exception {
      Job job = Job.getInstance(getConf(), "movie_rating");
      job.setJarByClass(this.getClass());
      // Use TextInputFormat, the default unless job.setInputFormatClass is used
      FileInputFormat.addInputPath(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));
      job.setMapperClass(Map.class);
      job.setCombinerClass(Reduce.class);
      job.setReducerClass(Reduce.class);
      
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(DoubleWritable.class);
      
      return job.waitForCompletion(true) ? 0 : 1;
    }

    public static class Map extends Mapper<LongWritable, Text, Text, DoubleWritable> {
      private static final String COMMA_DELIMITER = ",";

      public void map(LongWritable offset, Text lineText, Context context)
          throws IOException, InterruptedException {
        String line = lineText.toString();
        if (line.isEmpty() || line.contains("userId"))
            return;
        
        String[] data = line.split(COMMA_DELIMITER);
        Text movie = new Text(data[1]);
        DoubleWritable score = new DoubleWritable(Double.parseDouble(data[2]));
        context.write(movie,score);       
      }
    }

    public static class Reduce extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
      @Override
      public void reduce(Text movieId, Iterable<DoubleWritable> scores, Context context)
          throws IOException, InterruptedException {
        double totalScores = 0.0;
        int count = 0;
        for (DoubleWritable socre : scores) {
            totalScores += socre.get();
            count++;
        }
        double avgScore = totalScores / count;
        context.write(movieId, new DoubleWritable(avgScore));
      }
    }
}
