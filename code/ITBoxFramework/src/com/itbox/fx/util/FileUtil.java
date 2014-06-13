package com.itbox.fx.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileUtil {

	/**
	 * 保存图片到指定的目录
	 * @param bit
	 * @param fileName 文件名
	 * @return
	 */
	public static String saveBitToSD(Bitmap bit, String fileName) {
		if (bit == null || bit.isRecycled()) return "";
		
		File file = new File(Environment.getExternalStorageDirectory(), "/");
		File dirFile = new File(file.getAbsolutePath());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File pathFile = new File(dirFile, fileName);
		if (pathFile.exists()) {
			return pathFile.getAbsolutePath();
		} else {
			ImageUtils.Bitmap2File(bit, pathFile.getAbsolutePath());
			return pathFile.getAbsolutePath();
		}
	}
	
	/**
	 * Bitmap转换为文件
	 * 
	 * @param bitmap
	 * @param filename
	 */
	public static void Bitmap2File(Bitmap bitmap, String filename) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			fos.write(baos.toByteArray());
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 从SD卡加载图片
	 * 
	 * @param imagePath
	 * @return
	 */
	public static Bitmap getImageFromLocal(String imagePath) {
//		File file = new File(imagePath);
//		if (file.exists()) {
//			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//			// file.setLastModified(System.currentTimeMillis());
//			return bitmap;
//		}
//       
//		return null;
		try {
			FileInputStream fis = new FileInputStream(imagePath);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
