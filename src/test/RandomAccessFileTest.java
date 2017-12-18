package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException{
        //append new content at the end of file
        RandomAccessFile rFile = new RandomAccessFile("c:\\tmp\\rFile.txt","rw");
        FileChannel rChannel = rFile.getChannel();
        ByteBuffer buffer = ByteBuffer.wrap("new content\n".getBytes());
        rChannel.position(rChannel.size());
        rChannel.write(buffer);
        rFile.close(); 
        
        //read the file
        RandomAccessFile rrFile = new RandomAccessFile("c:\\tmp\\rFile.txt","rw");
        FileChannel rrChannel = rrFile.getChannel();
        
        ByteBuffer rBuffer = ByteBuffer.allocate(1024);
        while(rrChannel.read(rBuffer)!=-1){
            rBuffer.flip();
            for(int i=0;i<rBuffer.limit();i++){
                System.out.print((char) rBuffer.get());
            }
            rBuffer.clear();
        }
        
        
       
        
        
        
    }

}
