//Copyright (c) 2016 by Disy Informationssysteme GmbH
package net.disy.biggis.kef.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// NOT_PUBLISHED
@Service
public class KefImportFileService {

	@Value("${ffImportDirectory}")
	private String fallenfaengePath;

	@Value("${eiImportDirectory}")
	private String eiablagePath;

	@Value("${mapImportDirectory}")
	private String mapPath;

	public void writeChartsFallenfaenge(int id, int year, String data) throws IOException {
		writeData(data, fallenfaengePath + "/" + year, "kef-ff-" + id + ".json");
	}

	public void writeChartsEiablageBeeren(int id, int year, String data) throws IOException {
		writeData(data, eiablagePath + "/" + "beeren" + "/" + year, "kef-ei-beeren-" + id + ".json");
	}

	public void writeChartsEiablageFunde(int id, int year, String data) throws IOException {
		writeData(data, eiablagePath + "/" + "funde" + "/" + year, "kef-ei-funde-" + id + ".json");
	}

	public void writeMapFallenfaenge(int year, String data) throws IOException {
		writeData(data, mapPath, "map-kef-ff-" + year + ".json");
	}

	public void writeMapEiablageBeeren(int year, String data) throws IOException {
		writeData(data, mapPath, "map-kef-ei-beeren-" + year + ".json");
	}

	public void writeMapEiablageFunde(int year, String data) throws IOException {
		writeData(data, mapPath, "map-kef-ei-funde-" + year + ".json");
	}

	private void writeData(String data, String pathName, String fileName) throws IOException, FileNotFoundException {
		File importDirectory = createDirectoryIfNotExists(pathName);
		File file = createNewFile(importDirectory, fileName);
		writeData(file, data);
	}

	private void writeData(File file, String data) throws IOException, FileNotFoundException {
		try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
			outputStream.write(data.getBytes(Charsets.UTF_8));
		}
	}

	private File createNewFile(File importDirectory, String fileName) throws IOException {
		File file = new File(importDirectory, fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	private File createDirectoryIfNotExists(String pathname) {
		File importDirectory = new File(pathname);
		if (!importDirectory.exists()) {
			importDirectory.mkdirs();
		}
		return importDirectory;
	}

}
