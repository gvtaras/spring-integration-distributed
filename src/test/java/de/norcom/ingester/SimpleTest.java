package de.norcom.ingester;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

public class SimpleTest {

	String rootStr = "/tmp/tree/";
	int count = 10;
	int counterFile = 0;
	int counterDir = 0;

	@Ignore
	@Test
	public void deleteTree() throws Exception {

		Path testDir = Paths.get(rootStr);
		if (Files.exists(testDir)) {
			System.out.println("Removing directory: " + testDir);
			Files.walk(testDir)
					.sorted(Comparator.reverseOrder())
					.map(Path::toFile)
					.forEach(file -> {
						if(file.isFile()) {
							counterFile ++;
						} else {
							counterDir ++;
						}
						System.out.println(file + " " + counterFile + " " + counterDir);
						file.delete();
					});
		}
	}


	@Test
	public void createTree() throws Exception {

		createOneLevel(rootStr, 2);
	}

	private void createOneLevel(String rootStr, int countDown) throws IOException {

		System.out.println(rootStr + " " + countDown + " " + counterFile + " " + + counterDir);

		if (countDown < 0) {
			return;
		}

		Path path = Paths.get(rootStr);
		Files.createDirectories(path);

		for (int i = 0; i < count; i++) {
			String newRoot = rootStr + UUID.randomUUID().toString() + "/";
			Files.createDirectories(Paths.get(newRoot));
			Path file = Paths.get(rootStr + UUID.randomUUID().toString() + ".txt");
			Files.createFile(file);
			counterFile ++;
			counterDir ++;
			createOneLevel(newRoot, countDown - 1);
		}
	}
}
