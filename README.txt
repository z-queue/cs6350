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



# Assignment 1b
--------------------------------------------------------
Part 1
--------------------------------------------------------
Part 2

According to README.html from movielens.org, the rating data file structure is as described below:

### Ratings Data File Structure (ratings.csv)
All ratings are contained in the file ratings.csv. Each line of this file after the header row represents one rating of one movie by one user, and has the following format:

userId,movieId,rating,timestamp
The lines within this file are ordered first by userId, then, within user, by movieId.

Ratings are made on a 5-star scale, with half-star increments (0.5 stars - 5.0 stars).

Timestamps represent seconds since midnight Coordinated Universal Time (UTC) of January 1, 1970.


hdfs dfs -rm -r assignment1b

hadoop jar assignment-0.0.1-SNAPSHOT.jar \
  assignment1b.MovieRating \
  hdfs://cshadoop1/movielens/ratings.csv \
  hdfs://cshadoop1/user/<net-id>/assignment1b 

# Verify Results
hdfs dfs -ls assignment1b
hdfs dfs -get assignment1b/part-r-00000
vim part-r-00000

Requirements:
- In Mapper:
    read in the ratings.csv file
        use the 'movieId' s the key and the 'rating' as the value
- In Reducer:
    compute the averate rating (as a double datatype) for each 'movieId' and store it in the output direcotry on HDFS.

