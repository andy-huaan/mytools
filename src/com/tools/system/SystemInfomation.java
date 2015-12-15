package com.tools.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sun.management.OperatingSystemMXBean;

/**
 * 获取系统相关信息
 * @author Administrator
 *
 */
public class SystemInfomation {
	private static String linuxVersion = null;
	private static final int CPUTIME = 30;   
	private static final int PERCENT = 100;   
	private static final int FAULTLENGTH = 10;  
	
	public static void main(String[] args) {
		SystemInfomation si = new SystemInfomation();
		si.systemPid();
		si.systemMemory();
		si.systemMemory2();
		si.systemName();
		System.out.println(si.getCpuRatioForWindows());
	}
	
	/**
	 * 获取当前应用程序进程号
	 */
	private void systemPid(){
		String info = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
		System.out.println("当前应用程序进程号是："+info);
	}
	
	/**
	 * 获取系统物理内存使用情况
	 */
	private void systemMemory(){
		OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("系统物理内存总计：" + osmb.getTotalPhysicalMemorySize() / 1024/1024 + "MB"); 
        System.out.println("系统物理可用内存总计：" + osmb.getFreePhysicalMemorySize() / 1024/1024 + "MB");
	}
	
	/**
	 * 获取系统可用内存使用情况
	 */
	private void systemMemory2(){
		// 返回 Java 虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory() / 1012/1024;   
        // 返回 Java 虚拟机中的空闲内存量   
        long freeMemory = Runtime.getRuntime().freeMemory() / 1012/1024;   
        // 返回 Java 虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1012/1024;
        System.out.println("可用内存:"+totalMemory+"剩余内存:"+freeMemory+"最大可用内存:"+maxMemory);
	}
	
	/**
	 * 获取操作系统的名称
	 */
	private void systemName(){
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		Properties props = System.getProperties(); //获得系统属性集 
		String osArch = props.getProperty("os.arch"); //操作系统构架    
		String osVersion = props.getProperty("os.version"); //操作系统版本  
		//一些常用的信息获得
		/*java.version    Java 运行时环境版本  
		java.vendor     Java 运行时环境供应商  
		java.vendor.url     Java 供应商的 URL  
		java.home   Java 安装目录  
		java.vm.specification.version   Java 虚拟机规范版本  
		java.vm.specification.vendor    Java 虚拟机规范供应商  
		java.vm.specification.name  Java 虚拟机规范名称  
		java.vm.version     Java 虚拟机实现版本  
		java.vm.vendor  Java 虚拟机实现供应商  
		java.vm.name    Java 虚拟机实现名称  
		java.specification.version  Java 运行时环境规范版本  
		java.specification.vendor   Java 运行时环境规范供应商  
		java.specification.name     Java 运行时环境规范名称  
		java.class.version  Java 类格式版本号  
		java.class.path     Java 类路径  
		java.library.path   加载库时搜索的路径列表  
		java.io.tmpdir  默认的临时文件路径  
		java.compiler   要使用的 JIT 编译器的名称  
		java.ext.dirs   一个或多个扩展目录的路径  
		os.name     操作系统的名称  
		os.arch     操作系统的架构  
		os.version  操作系统的版本  
		file.separator  文件分隔符（在 UNIX 系统中是“/”）  
		path.separator  路径分隔符（在 UNIX 系统中是“:”）  
		line.separator  行分隔符（在 UNIX 系统中是“/n”）  
		user.name   用户的账户名称  
		user.home   用户的主目录  
		user.dir    用户的当前工作目录 */ 
	}
	
	/**
	 * Linux下获取CPU使用率
	 * @return
	 */
	private static double getCpuRateForLinux(){   
        InputStream is = null;   
        InputStreamReader isr = null;   
        BufferedReader brStat = null;   
        StringTokenizer tokenStat = null;   
        try{   
            System.out.println("Get usage rate of CUP , linux version: "+linuxVersion);   
  
            Process process = Runtime.getRuntime().exec("top -b -n 1");   
            is = process.getInputStream();                     
            isr = new InputStreamReader(is);   
            brStat = new BufferedReader(isr);   
             
            if("2.4".equals(linuxVersion)){   
                brStat.readLine();   
                brStat.readLine();   
                brStat.readLine();   
                brStat.readLine();   
                 
                tokenStat = new StringTokenizer(brStat.readLine());   
                tokenStat.nextToken();   
                tokenStat.nextToken();   
                String user = tokenStat.nextToken();   
                tokenStat.nextToken();   
                String system = tokenStat.nextToken();   
                tokenStat.nextToken();   
                String nice = tokenStat.nextToken();   
                 
                System.out.println(user+" , "+system+" , "+nice);   
                 
                user = user.substring(0,user.indexOf("%"));   
                system = system.substring(0,system.indexOf("%"));   
                nice = nice.substring(0,nice.indexOf("%"));   
                 
                float userUsage = new Float(user).floatValue();   
                float systemUsage = new Float(system).floatValue();   
                float niceUsage = new Float(nice).floatValue();   
                 
                return (userUsage+systemUsage+niceUsage)/100;   
            }else{   
                brStat.readLine();   
                brStat.readLine();   
                     
                tokenStat = new StringTokenizer(brStat.readLine());   
                tokenStat.nextToken();   
                tokenStat.nextToken();   
                tokenStat.nextToken();   
                tokenStat.nextToken();   
                tokenStat.nextToken();   
                tokenStat.nextToken();   
                tokenStat.nextToken();   
                String cpuUsage = tokenStat.nextToken();   
                     
                 
                System.out.println("CPU idle : "+cpuUsage);   
                Float usage = new Float(cpuUsage.substring(0,cpuUsage.indexOf("%")));   
                 
                return (1-usage.floatValue()/100);   
            } 
              
        } catch(IOException ioe){   
            System.out.println(ioe.getMessage());   
            freeResource(is, isr, brStat);   
            return 1;   
        } finally{   
            freeResource(is, isr, brStat);   
        }   
  
    }   
	private static void freeResource(InputStream is, InputStreamReader isr, BufferedReader br){   
        try{   
            if(is!=null)   
                is.close();   
            if(isr!=null)   
                isr.close();   
            if(br!=null)   
                br.close();   
        }catch(IOException ioe){   
            System.out.println(ioe.getMessage());   
        }   
    } 
	
	/**  
     * Windows 获得CPU使用率.  
     * @return 返回cpu使用率   
     */   
    private double getCpuRatioForWindows() {   
        try {   
            String procCmd = System.getenv("windir")   
                    + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"   
                    + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";   
            // 取进程信息   
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));   
            Thread.sleep(CPUTIME);   
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));   
            if (c0 != null && c1 != null) {   
                long idletime = c1[0] - c0[0];   
                long busytime = c1[1] - c0[1];   
                return Double.valueOf(   
                        PERCENT * (busytime) / (busytime + idletime))   
                        .doubleValue();   
            } else {   
                return 0.0;   
            }   
        } catch (Exception ex) {   
            ex.printStackTrace();   
            return 0.0;   
        }   
    }   
  
    /**       
     * 读取CPU信息.  
     * @param proc  
     * @return
     */   
    private long[] readCpu(final Process proc) {   
        long[] retn = new long[2];   
        try {   
            proc.getOutputStream().close();   
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());   
            LineNumberReader input = new LineNumberReader(ir);   
            String line = input.readLine();   
            if (line == null || line.length() < FAULTLENGTH) {   
                return null;   
            }   
            int capidx = line.indexOf("Caption");   
            int cmdidx = line.indexOf("CommandLine");   
            int rocidx = line.indexOf("ReadOperationCount");   
            int umtidx = line.indexOf("UserModeTime");   
            int kmtidx = line.indexOf("KernelModeTime");   
            int wocidx = line.indexOf("WriteOperationCount");   
            long idletime = 0;   
            long kneltime = 0;   
            long usertime = 0;   
            while ((line = input.readLine()) != null) {   
                if (line.length() < wocidx) {   
                    continue;   
                }   
                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,   
                // ThreadCount,UserModeTime,WriteOperation   
                String caption = Bytes.substring(line, capidx, cmdidx - 1)   
                        .trim();   
                String cmd = Bytes.substring(line, cmdidx, kmtidx - 1).trim();   
                if (cmd.indexOf("wmic.exe") >= 0) {   
                    continue;   
                }   
                // log.info("line="+line);   
                if (caption.equals("System Idle Process")   
                        || caption.equals("System")) {   
                    idletime += Long.valueOf(   
                            Bytes.substring(line, kmtidx, rocidx - 1).trim())   
                            .longValue();   
                    idletime += Long.valueOf(   
                            Bytes.substring(line, umtidx, wocidx - 1).trim())   
                            .longValue();   
                    continue;   
                }   
  
                kneltime += Long.valueOf(   
                        Bytes.substring(line, kmtidx, rocidx - 1).trim())   
                        .longValue();   
                usertime += Long.valueOf(   
                        Bytes.substring(line, umtidx, wocidx - 1).trim())   
                        .longValue();   
            }   
            retn[0] = idletime;   
            retn[1] = kneltime + usertime;   
            return retn;   
        } catch (Exception ex) {   
            ex.printStackTrace();   
        } finally {   
            try {   
                proc.getInputStream().close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
        return null;   
    } 
}
class Bytes {
    public static String substring(String src, int start_idx, int end_idx){
        byte[] b = src.getBytes();   
        String tgt = "";
        for(int i=start_idx; i<=end_idx; i++){
            tgt +=(char)b[i];
        }
        return tgt;
    }
}
