package assignment1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.apache.http.client.params.AllClientPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipFileDecompressor {

    static private final Logger log = LoggerFactory.getLogger(ZipFileDecompressor.class);
    
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        OutputStream out = null;
        ZipInputStream zin = null;
        try {

            String zipUrl = getRealUrl(args[0]);
            String outputUri = args[1];

            URL url = new URL(zipUrl);
            String zipFilename = FilenameUtils.getName(url.getFile());
            
            log.info("Zip File Name: " + zipFilename);

            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(URI.create(outputUri), conf);

            Path outputPath = new Path(outputUri, "assignment1");
            if (!fs.exists(outputPath)) {
                fs.mkdirs(outputPath);
            }

            zin = new ZipInputStream(new BufferedInputStream(url.openStream()));

            ZipEntry zipentry;
            ;
            while ((zipentry = zin.getNextEntry()) != null) {
                try {
                    String filename = zipentry.getName();
                    Path outputFilePath = new Path(outputPath, filename);
                    log.info("File name: " + filename);
                    log.info("Output path: " + outputFilePath);
                    out = fs.create(outputFilePath, new Progressable() {
                        public void progress() {
                            System.out.print(".");
                          }
                        }); 
                    IOUtils.copyBytes(zin, out, 4096, false);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.closeStream(out);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(zin);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("");
        log.info("Program execution time:  " + (endTime - startTime) + " milliseconds");
    }
    
    
    /**
     * Helper method that follows HTTP redirect headers to get real resource url.
     * @param url
     * @return
     */
    private static String getRealUrl(String url) {
        try {
            HttpClient client = new HttpClient();
            HttpMethod method = new HeadMethod(url);
            HttpParams params = client.getParams();
            params.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);
            client.executeMethod(method);
            url = method.getURI().getURI();
            
            log.info("Resource Location: " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return url;
        
    }
    

}
