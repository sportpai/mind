package com.mind.cache;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.BitSet;
import java.util.Set;

/**
 * Created by wangyunlong on 18-3-20.
 */
public class FileChannelTest {

    public static void main(String[] args) {
        String filePath2 = "/home/wangyunlong/demo.zip";
        readFile4(filePath2);
    }
    /**
     * NIO 内存映射读大文件
     * @author linbingwen
     * @since  2015年9月15日
     * @param path
     */
    public static void readFile4(String path) {
        try {
            int memlength = 16;
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(memlength);

            Path p = Paths.get(path);
            if(!Files.exists(p, LinkOption.NOFOLLOW_LINKS)){
                Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
                FileAttribute<Set<PosixFilePermission>> attrs = PosixFilePermissions.asFileAttribute(perms);
                Files.createFile(p, attrs);
            }
            FileChannel fileChannel = FileChannel.open(p, StandardOpenOption.READ,StandardOpenOption.WRITE);

            ByteBuffer byteBufferTmp = ByteBuffer.allocateDirect(memlength);

            //读取数据，写入byteBuffer
            fileChannel.read(byteBufferTmp);
            BitSet bitSet = new BitSet(memlength);

            //{0, 3, 7, 8, 10, 13}
            bitSet.set(0,true);
            bitSet.set(3,true);
            bitSet.set(7,true);
            bitSet.set(8,true);
            bitSet.set(10,true);
            bitSet.set(13,true);

            byteBuffer.put(bitSet.toByteArray());
            System.out.println(bitSet);
            System.out.println("byteBuffer.equals(byteBufferTmp):" + byteBuffer.equals(byteBufferTmp));

            //变读为写
            byteBuffer.flip();
            //读取byteBuffer，写入数据
            //fileChannel.write(byteBuffer);

            Long fileSize = fileChannel.size();

            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<fileSize;i++){
                byte b  = mappedByteBuffer.get(i);
                sb.append(Byte.toUnsignedInt(b));
            }
            if(sb.length() > 0){
                String str = sb.toString();
                int value = Integer.parseInt(sb.toString());
                String binary = Integer.toBinaryString(value);
                String hexStr = Integer.toHexString(value);


                System.out.println("str:" + str);
                System.out.println("value:" + value);
                System.out.println("binary:" + binary);
                System.out.println("hexStr:" + hexStr);

                System.out.println("fileSize:" + fileSize);
            }

            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
