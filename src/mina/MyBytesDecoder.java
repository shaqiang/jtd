/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package mina;

import java.io.Serializable;
import java.nio.ByteOrder;
import java.rmi.ServerException;
import java.util.Iterator;

import org.apache.mina.core.buffer.BufferDataException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * A {@link ProtocolDecoder} which deserializes {@link Serializable} Java
 * objects using {@link IoBuffer#getObject(ClassLoader)}.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class MyBytesDecoder extends CumulativeProtocolDecoder {
    private final ClassLoader classLoader;

    private int maxObjectSize = 1048576; // 1MB

    /**
     * Creates a new instance with the {@link ClassLoader} of
     * the current thread.
     */
    public MyBytesDecoder() {
        this(Thread.currentThread().getContextClassLoader());
    }

    /**
     * Creates a new instance with the specified {@link ClassLoader}.
     */
    public MyBytesDecoder(ClassLoader classLoader) {
        if (classLoader == null) {
            throw new IllegalArgumentException("classLoader");
        }
        this.classLoader = classLoader;
    }

    /**
     * Returns the allowed maximum size of the object to be decoded.
     * If the size of the object to be decoded exceeds this value, this
     * decoder will throw a {@link BufferDataException}.  The default
     * value is <tt>1048576</tt> (1MB).
     */
    public int getMaxObjectSize() {
        return maxObjectSize;
    }

    /**
     * Sets the allowed maximum size of the object to be decoded.
     * If the size of the object to be decoded exceeds this value, this
     * decoder will throw a {@link BufferDataException}.  The default
     * value is <tt>1048576</tt> (1MB).
     */
    public void setMaxObjectSize(int maxObjectSize) {
        if (maxObjectSize <= 0) {
            throw new IllegalArgumentException("maxObjectSize: " + maxObjectSize);
        }

        this.maxObjectSize = maxObjectSize;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
    	
    	in.order(ByteOrder.LITTLE_ENDIAN);
    	
    	  if (in.remaining() > 1) {
    		 // 
    		 
             int length = ((in.get(in.position()+8)&0xff)<<8)+(in.get(in.position()+9)&0xff)+4;
            // System.out.println("length is"+length);
    		 // int length = in.limit();
              if (length < 1) {
                  throw new ServerException("Error net message. (Message Length="+length+")");
              }
              if (length > 1024*1024) {
                  throw new ServerException("Error net message. Message Length("+length+") > MessageMaxByte("+1024*1024+")");
              }
              if (length > in.remaining()) return false;
              //复制一个完整消息
              byte[] bytes = new byte[length];
              in.get(bytes);
              
              out.write(IoBuffer.wrap(bytes));
              return true;
          } else {
              return false;
          }
//    
//    	in.order(ByteOrder.LITTLE_ENDIAN);
//
//    	if(in.remaining()<4)//这里很关键，网上很多代码都没有这句，是用来当拆包时候剩余长度小于4的时候的保护，不加就出错咯 
//    	{ 
//    	return false;
//    	}
//    	        if (in.remaining() > 1) {
//    	         
//    	            in.mark();//标记当前位置，以便reset
//    	            int length = ((in.get(in.position()+8)&0xff)<<8)+(in.get(in.position()+9)&0xff)+4;
//    	            if(length < 0 ){//如果消息内容不够，则重置，相当于不读取size   
//    	            System.out.println("package notenough  left="+in.remaining()+" length="+length);
//    	                in.reset();   
//    	                return false;//接收新数据，以拼凑成完整数据   
//    	            }else{  
//    	                byte[] bytes = new byte[length]; 
//    	                in.get(bytes, 0, length);   
//    	                in.reset();
//    	                if(null != bytes && bytes.length > 0){   
//    	                	out.write(IoBuffer.wrap(bytes)); 
//    	                	
//    	                }   
//    	                if(in.remaining() > 0){//如果读取内容后还粘了包，就让父类再给一次，进行下一次解析  
//    	                System.out.println("package left="+in.remaining()+" data="+in.toString()); 
//    	                	return false;
//    	                } 
//    	                return true;
//    	                //这里有两种情况1：没数据了，那么就结束当前调用，有数据就再次调用
//    	            }   
//    	        }   
//    	        return false;//处理成功，让父类进行接收下个包   
    }
    
}
