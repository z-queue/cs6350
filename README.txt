Part 1: Bz2 Depression and HDFS File Uploader

Compile:
- Run 
$ mvn package 
assignment-0.0.1-SNAPSHOT.jar will be generated inside target folder.

# Delete assignment1 if exists
hdfs dfs -rm -r assignment1

# Download files from remote, then depress and upload to HDFS.
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/20417.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/5000-8.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/132.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/1661-8.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/972.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/19699.txt.bz2 hdfs://cshadoop1/user/<net-id>

# Verify Results
hdfs dfs -ls assignment1


Development Document References 

CompressionCodec, CompressionCodecFactory, FileSystem, Path
https://hadoop.apache.org/docs/r2.4.1/api/


--------------------------------------------------------
Part 2: Zip Depression and HDFS File Uploader


# Wiki corpus 
http://corpus.byu.edu/spantext-samples/text.zip


$ hadoop jar assignment-0.0.1-SNAPSHOT.jar \
    assignment1.ZipFileDecompressor \
    http://corpus.byu.edu/wikitext-samples/text.zip \
    hdfs://cshadoop1/user/<net-id>


# Verify Results
hdfs dfs -ls assignment1



