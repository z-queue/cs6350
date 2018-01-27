hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/20417.txt.bz2 hdfs://cshadoop1/user/zxl165030


wget http://www.utdallas.edu/~axn112530/cs6350/lab2/input/20417.txt.bz2 -O 20417-origin.txt.bz2

bzip2 -d 20417-origin.txt.bz2

md5sum 20417-origin.txt 20417.txt


Compile:
- Run 
$ mvn package 
assignment-0.0.1-SNAPSHOT.jar will be generated inside target folder.

# Delete assignment1 if exists
hdfs dfs -rm -r assignment1

# Download files from remote, then depress and upload to HDFS.
hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/20417.txt.bz2 hdfs://cshadoop1/user/zxl165030
hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/5000-8.txt.bz2 hdfs://cshadoop1/user/zxl165030
hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/132.txt.bz2 hdfs://cshadoop1/user/zxl165030
hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/1661-8.txt.bz2 hdfs://cshadoop1/user/zxl165030
hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/972.txt.bz2 hdfs://cshadoop1/user/zxl165030
hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/19699.txt.bz2 hdfs://cshadoop1/user/zxl165030

# Verify Results
hdfs dfs -ls assignment1


Development Document References 

CompressionCodec, CompressionCodecFactory, FileSystem, Path
https://hadoop.apache.org/docs/r2.4.1/api/


--------------------------------------------------------
Part 2


wget http://corpus.byu.edu/wikitext-samples/text.zip

hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.ZipFileDecompressor https://corpus.byu.edu/wikitext-samples/text.zip hdfs://cshadoop1/user/zxl165030


hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.ZipFileDecompressor http://corpus.byu.edu/spantext-samples/text.zip hdfs://cshadoop1/user/zxl165030


  hadoop jar assignment-0.0.1-SNAPSHOT.jar \
      assignment1.ZipFileDecompressor \
      http://corpus.byu.edu/wikitext-samples/text.zip \
      hdfs://cshadoop1/user/zxl165030/assignment1/part2/wiki/


# Verify Results
hdfs dfs -ls assignment1


https://corpus.byu.edu/wikitext-samples/text.zip

curl -LH http://corpus.byu.edu/wikitext-samples/text.zip
curl -Ls -o /dev/null -w %{url_effective} http://corpus.byu.edu/wikitext-samples/text.zip



