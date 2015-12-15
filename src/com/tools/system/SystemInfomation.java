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
 * ��ȡϵͳ�����Ϣ
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
	 * ��ȡ��ǰӦ�ó�����̺�
	 */
	private void systemPid(){
		String info = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
		System.out.println("��ǰӦ�ó�����̺��ǣ�"+info);
	}
	
	/**
	 * ��ȡϵͳ�����ڴ�ʹ�����
	 */
	private void systemMemory(){
		OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("ϵͳ�����ڴ��ܼƣ�" + osmb.getTotalPhysicalMemorySize() / 1024/1024 + "MB"); 
        System.out.println("ϵͳ��������ڴ��ܼƣ�" + osmb.getFreePhysicalMemorySize() / 1024/1024 + "MB");
	}
	
	/**
	 * ��ȡϵͳ�����ڴ�ʹ�����
	 */
	private void systemMemory2(){
		// ���� Java ������е��ڴ�����
        long totalMemory = Runtime.getRuntime().totalMemory() / 1012/1024;   
        // ���� Java ������еĿ����ڴ���   
        long freeMemory = Runtime.getRuntime().freeMemory() / 1012/1024;   
        // ���� Java �������ͼʹ�õ�����ڴ���
        long maxMemory = Runtime.getRuntime().maxMemory() / 1012/1024;
        System.out.println("�����ڴ�:"+totalMemory+"ʣ���ڴ�:"+freeMemory+"�������ڴ�:"+maxMemory);
	}
	
	/**
	 * ��ȡ����ϵͳ������
	 */
	private void systemName(){
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		Properties props = System.getProperties(); //���ϵͳ���Լ� 
		String osArch = props.getProperty("os.arch"); //����ϵͳ����    
		String osVersion = props.getProperty("os.version"); //����ϵͳ�汾  
		//һЩ���õ���Ϣ���
		/*java.version    Java ����ʱ�����汾  
		java.vendor     Java ����ʱ������Ӧ��  
		java.vendor.url     Java ��Ӧ�̵� URL  
		java.home   Java ��װĿ¼  
		java.vm.specification.version   Java ������淶�汾  
		java.vm.specification.vendor    Java ������淶��Ӧ��  
		java.vm.specification.name  Java ������淶����  
		java.vm.version     Java �����ʵ�ְ汾  
		java.vm.vendor  Java �����ʵ�ֹ�Ӧ��  
		java.vm.name    Java �����ʵ������  
		java.specification.version  Java ����ʱ�����淶�汾  
		java.specification.vendor   Java ����ʱ�����淶��Ӧ��  
		java.specification.name     Java ����ʱ�����淶����  
		java.class.version  Java ���ʽ�汾��  
		java.class.path     Java ��·��  
		java.library.path   ���ؿ�ʱ������·���б�  
		java.io.tmpdir  Ĭ�ϵ���ʱ�ļ�·��  
		java.compiler   Ҫʹ�õ� JIT ������������  
		java.ext.dirs   һ��������չĿ¼��·��  
		os.name     ����ϵͳ������  
		os.arch     ����ϵͳ�ļܹ�  
		os.version  ����ϵͳ�İ汾  
		file.separator  �ļ��ָ������� UNIX ϵͳ���ǡ�/����  
		path.separator  ·���ָ������� UNIX ϵͳ���ǡ�:����  
		line.separator  �зָ������� UNIX ϵͳ���ǡ�/n����  
		user.name   �û����˻�����  
		user.home   �û�����Ŀ¼  
		user.dir    �û��ĵ�ǰ����Ŀ¼ */ 
	}
	
	/**
	 * Linux�»�ȡCPUʹ����
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
     * Windows ���CPUʹ����.  
     * @return ����cpuʹ����   
     */   
    private double getCpuRatioForWindows() {   
        try {   
            String procCmd = System.getenv("windir")   
                    + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"   
                    + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";   
            // ȡ������Ϣ   
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
     * ��ȡCPU��Ϣ.  
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
                // �ֶγ���˳��Caption,CommandLine,KernelModeTime,ReadOperationCount,   
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
