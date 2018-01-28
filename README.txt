1) The project use Maven to manage its dependencies, to compile the entire project run 
$ mvn package or open with Eclipse > right click project root > Run As > Maven Build... > 
type package in "Goals" field > Run

The compiled assignment-0.0.1-SNAPSHOT.jar will be generated inside target folder.

2) To run the program, place the assignment-0.0.1-SNAPSHOT.jar to Hadoop cluster and 
follow the commands below.

3) The downloaded file will be place under <Your-hdfs-directory>/assignment1 folder. 
For example, if you specify hdfs://cshadoop1/user/<net-id> as output directory, 
the document will go to hdfs://cshadoop1/user/<net-id>/assignment1

Part 1: Bz2 Depression and HDFS File Uploader

# Delete assignment1 if exists
hdfs dfs -rm -r assignment1

# Download files from remote, then depress and upload to HDFS.
# hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor <bz2-doc-uri> <hdfs-uri>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/20417.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/5000-8.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/132.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/1661-8.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/972.txt.bz2 hdfs://cshadoop1/user/<net-id>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.Bz2FileDecompressor http://www.utdallas.edu/~axn112530/cs6350/lab2/input/19699.txt.bz2 hdfs://cshadoop1/user/<net-id>

# Verify Results
hdfs dfs -ls assignment1


--------------------------------------------------------
Part 2: Zip Depression and HDFS File Uploader

# Data Sets: https://www.corpusdata.org/formats.asp

# Wikipedia (1.8 billion)
# hadoop jar assignment-0.0.1-SNAPSHOT.jar assignment1.ZipFileDecompressor <zip-file-uri> <hdfs-uri>
$ hadoop jar assignment-0.0.1-SNAPSHOT.jar \
    assignment1.ZipFileDecompressor \
    http://corpus.byu.edu/wikitext-samples/text.zip \
    hdfs://cshadoop1/user/<net-id>


# Verify Results
hdfs dfs -ls assignment1



