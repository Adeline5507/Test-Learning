package test.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class NioFileCopyTest {

    public static void main(String[] args) throws IOException{
        
            FileInputStream sourceFile = new FileInputStream(new File("C:\\tmp\\source.txt"));
            FileOutputStream destFile = new FileOutputStream(new File("C:\\tmp\\dest.txt")) ;
            FileChannel sChannel = sourceFile.getChannel();
            FileChannel dChannel = destFile.getChannel();
            
            sChannel.transferTo(0, sChannel.size(), dChannel);//使用 transferTo 拷贝(也可使用transferFrom)
            
            FileOutputStream destFile2 = new FileOutputStream(new File("C:\\tmp\\dest2.txt")) ;//使用buffer直接往目标文件写入的方式拷贝
            FileChannel dChannel2 = destFile2.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            
            while(sChannel.read(buffer)!=-1){ 
                buffer.flip();
                dChannel2.write(buffer);
                buffer.clear();
            }
            
            Path sPath = Paths.get("C:\\tmp", "source.txt");
            Path dPath = Paths.get("C:\\tmp", "dest3.txt");
            Files.copy(sPath, dPath);//使用copy函数
            
            sourceFile.close();
            destFile.close();
            destFile2.close();
            
                     
        
    }
}
