package P1;

import java.io.*;

public class MagicSquare {
	public static boolean isLegalMagicSquare(String fileName) {
		int i = 0, j = 0, size = 150, lineCount = 0, rows = 0, theCount = 0, specialCount = 0;
		int[][] matrix = new int[size][size];
		int[] columns = new int[size];
		String[] temp = new String[size];
		try {
			String filepath = "src/P1/txt/" + fileName;
			File file = new File(filepath);
			// InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			line = br.readLine();
			if (line == null) {
				System.out.println("nothing loaded\n");
				br.close();
				return false;
			}

			temp = line.split("\t");
			for (i = 0; i < temp.length; i++) {
				matrix[rows][i] = Integer.valueOf(temp[i]);
				theCount += matrix[rows][i];
			}
			columns[rows] = i;
			rows++;
			while ((line = br.readLine()) != null) {
				temp = line.split("\t");
				for (i = 0; i < temp.length; i++) {
					matrix[rows][i] = Integer.valueOf(temp[i]);
				}
				columns[rows] = i;
				rows++;
			}
			br.close();

		} catch (NumberFormatException NumberError) {
			System.out.println("矩阵内容错误\n");
			NumberError.printStackTrace();
			return false;
		} catch (IOException IOerror) {
			System.out.println("文件读取异常\n");
			IOerror.printStackTrace();
			return false;
		}

		for (i = 0, lineCount = 0; i < rows; i++, lineCount = 0) {
			if (rows != columns[i]) {
				System.out.println("矩阵并非方阵");
				return false;
			}
			for (j = 0; j < columns[i]; j++) {
				lineCount += matrix[i][j];
			}
			if (lineCount != theCount) {
				return false;
			}
		}

		for (i = 0, j = 0, lineCount = 0; i < rows; i++, lineCount = 0) {
			for (j = 0; j < rows; j++) {
				lineCount += matrix[j][i];
				if (i == j) {
					specialCount += matrix[i][j];
				}
			}

			if (lineCount != theCount) {
				return false;
			}
		}
		if (specialCount != theCount) {
			return false;
		}
		return true;

	}

	public static boolean generateMagicSquare(int n) {
		try {
			int magic[][] = new int[n][n];
			int row = 0, col = n / 2, i, j, square = n * n;
			String temp;

			for (i = 1; i <= square; i++) {
				magic[row][col] = i;
				if (i % n == 0)
					row++;
				else {
					if (row == 0)
						row = n - 1;
					else
						row--;
					if (col == (n - 1))
						col = 0;
					else
						col++;
				}
			}

			File filename = new File("src/P1/txt/6.txt");
			filename.createNewFile();
			FileWriter fileout = new FileWriter(filename);
			for (i = 0, temp = ""; i < n; i++, temp = "") {
				for (j = 0; j < n - 1; j++) {
					temp += (String.valueOf(magic[i][j]) + "\t");
				}
				temp += (String.valueOf(magic[i][j]) + "\n");
				fileout.write(temp);
			}
			fileout.close();

			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					System.out.print(magic[i][j] + "\t");
				System.out.println();
			}
			return true;

		} catch (ArrayIndexOutOfBoundsException ArrayIndexerror) {
			System.out.println("false,n is even\n");
			return false;
		} catch (NegativeArraySizeException Negativeerror) {
			System.out.println("false,n is negative\n");
			return false;
		} catch (IOException IOerror) {
			System.out.println("文件读写异常");
			IOerror.printStackTrace();
		}

		return true;

	}

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(isLegalMagicSquare("1.txt"));
		System.out.println(isLegalMagicSquare("2.txt"));
		System.out.println(isLegalMagicSquare("3.txt"));
		System.out.println(isLegalMagicSquare("4.txt"));
		System.out.println(isLegalMagicSquare("5.txt"));
		int matrixN = 5;//Integer.valueOf(br.readLine());
		generateMagicSquare(matrixN);
		System.out.println(isLegalMagicSquare("6.txt"));

	}
}
