package com.swamedia.learn.wsjersey.service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONObject;

import com.swamedia.learn.wsjersey.common.MasterConnection;
import com.swamedia.learn.wsjersey.common.MyMap;

@Path("/mahasiswa")
public class MahasiswaService extends MasterConnection {

	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	public Object getAllBarang(@Context UriInfo uriInfo, @Context HttpServletRequest hsr) {
		createConnection();
		List<MyMap> listBarang = jt.queryList("select * from MAHASISWA", new MyMap());
		closeConnection();

		return listBarang;
	}

	@GET
	@Path("/get/{nrp}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getMahasiswaByID(@PathParam("nrp") Integer nrp) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusId", "1");
		result.put("message", "INQUIRY BERHASIL");
		System.out.println("id : " + nrp);
		try {
			createConnection();
			MyMap Mahasiswa = (MyMap) jt.queryObject("select * from MAHASISWA where NRP=?", new Object[] { nrp },
					new MyMap());
			closeConnection();
			if (Mahasiswa != null) {
				result.put("result", Mahasiswa);
			}
		} catch (Exception e) {
			result.put("message", "GAGAL KARENA : " + e.getMessage());
			// TODO: handle exception
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insert")
	public Object createStd(@Context HttpServletRequest hsr) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		JSONObject request = null;
		MyMap respon = new MyMap();
		MyMap data = new MyMap();
		DataInputStream in;
		try {
			createConnection();
			in = new DataInputStream(new BufferedInputStream(hsr.getInputStream()));

			while ((line = in.readLine()) != null)
				sb.append(line);
			System.out.println("line : " + line);
			System.out.println("sb: " + sb.toString());
			// JSONObject json = new JSONObject(sb.toString());
			request = new JSONObject(sb.toString());

			// if (request == null) {
			// respon.put("Message", "Data Yang dikirim tidak ditemukan");
			// respon.put("rCode", "99");
			// respon.put("statusId", "0");
			// return respon;
			// }
			data.put("nrp", request.getInt("nrp"));
			data.put("nama", request.getString("nama"));
			data.put("jurusan", request.getString("jurusan"));
			data.put("fakultas", request.getString("fakultas"));
			data.put("angkatan", request.getString("jurusan"));

			jt.insert("mahasiswa", data);
			respon.put("message", "Data Berhasil Disimpan");
			respon.put("rCode", "00");
			respon.put("statusId", "1");
		} catch (Exception e) {
			e.printStackTrace();
			respon.put("message", e.getMessage());
			respon.put("rCode", "99");
			respon.put("statusId", "0");
			// TODO: handle exception
		}
		return respon;
	}

}
