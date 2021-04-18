package timo.home;

import java.util.ArrayList;

//JSON parsing JSON-simple library
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//JSON to IMUData
import com.google.gson.Gson;
import timo.home.data.IMUData;
import timo.home.data.IMUSample;

public class MoveJSONReader{
	private ArrayList<IMUSample> data;
	
	public double[] getAX(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
				a.add(data.get(i).ax);
		}
		return getArray(a);
	}
	
	public double[] getAY(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).ay);
		}
		return getArray(a);
	}
	
	public double[] getAZ(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).az);
		}
		return getArray(a);
	}
	
	public double[] getGX(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).gx);
		}
		return getArray(a);
	}
	
	public double[] getGY(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).gy);
		}
		return getArray(a);
	}
	
	public double[] getGZ(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).gz);
		}
		return getArray(a);
	}
	
	public double[] getMX(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).mx);
		}
		return getArray(a);
	}
	
	public double[] getMY(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).my);
		}
		return getArray(a);
	}
	
	public double[] getMZ(){
		ArrayList<Double> a = new ArrayList<Double>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).mz);
		}
		return getArray(a);
	}
	
	private double[] getArray(ArrayList<Double> a){
		double[] b = new double[a.size()];
		for (int i = 0;i<a.size();++i){b[i] = a.get(i);}
		return b;
	}

	public long[] getStamps(){
		ArrayList<Long> a = new ArrayList<Long>();
		for (int i = 0;i<data.size();++i){
			a.add(data.get(i).tStamp);
		}
		System.out.println(String.format("getStamps a.size() %03d",a.size()));
		return getLongArray(a);
	}
	
	private long[] getLongArray(ArrayList<Long> a){
		long[] b = new long[a.size()];
		for (int i = 0;i<a.size();++i){b[i] = a.get(i);}
		System.out.println(String.format("getLongArray b.length %03d",b.length));
		return b;
	}
	
	
	
	
	
	public static void main(String[] a){
		new MoveJSONReader(a[0]);
	}
	
	public MoveJSONReader(String filePath){
		data = new ArrayList<IMUSample>();
		
		
		  //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
			JSONObject meas = (JSONObject) obj.get("Meas");
			JSONArray imu = (JSONArray) meas.get("IMU9");
			
			for(int ii = 0;  ii<imu.size();++ii) {
				//System.out.println(String.format("\n\n\nObject %03d",ii));
				//System.out.println(imu.get(ii).toString());
				IMUData imuData = (new Gson()).fromJson(imu.get(ii).toString(), IMUData.class);
				for (int i = 0;i<imuData.accArray.length;++i){
					data.add(new IMUSample(imuData.timestamp,
											imuData.accArray[i].x,imuData.accArray[i].y,imuData.accArray[i].z,
											imuData.gyrArray[i].x,imuData.gyrArray[i].y,imuData.gyrArray[i].z,
											imuData.magArray[i].x,imuData.magArray[i].y,imuData.magArray[i].z,
											i));
				}
				
				//data.add((new Gson()).fromJson(imu.get(i).toString(), IMUData.class));
				//System.out.println(obj.get(key));
			}
			
			
			/*
				//System.out.println(obj);
			int cnt = 0;
			
			for(Iterator iterator = imu.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				System.out.println(String.format("\n\n\nObject %03d",cnt++));
				//System.out.println(obj.get(key));
			}
			*/
             
            //Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			 e.printStackTrace();
        }
		System.out.println(String.format("Got %03d",data.size()));
	}
	
}