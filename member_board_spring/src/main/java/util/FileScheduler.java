package util;

import java.io.File; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import service.BoardServiceImpl;
import vo.Attach;

public class FileScheduler {
	public static void main(String[] args) {
		File file = new File("c:\\upload\\210913");
		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println(f);
		}

		System.out.println("===========================================");

		List<Attach> dbList = new BoardServiceImpl().readAttachByPath("210913");
		for (Attach a : dbList) {
			System.out.println(a.getUuid());
		}
		System.out.println("===========================================");

		List<File> fileList = new ArrayList<>(Arrays.asList(files));
		List<File> existFile = new ArrayList<File>();
		for (File f : fileList) {
			for (Attach a : dbList) {
				if (f.getAbsolutePath().contains(a.getUuid())) {
					existFile.add(f);
				}
			}
		}

		System.out.println("===========================================");

		for (File f : existFile) {
			System.out.println(f);
		}

		fileList.removeAll(existFile);

		System.out.println("===========================================");

		for (File f : fileList) {
			System.out.println(f);
			f.delete();
		}

	}
}