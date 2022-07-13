import java.util.*;
import java.io.*;

public class tempAAChange{
  public static void main(String[] args) {
    List<String> listCsv = new ArrayList<String>();
    List<String> listBefore = new ArrayList<String>();
    List<String> listAfter = new ArrayList<String>();
    
    BufferedReader brCsv = null;
    BufferedReader brBef = null;

    try {
      /* csv�t�@�C���Ǎ� */
      File file = new File("./tempA.csv");
      FileInputStream input = new FileInputStream(file);
      InputStreamReader stream = new InputStreamReader(input);
      brCsv = new BufferedReader(stream);

      /* csv�t�@�C����list csv�Ɋi�[ */
      String line;
      while((line = brCsv.readLine()) != null){
        listCsv.add(line);
      }

      /* �e���v����java�t�@�C���Ǎ� */
      File fileBef = new File("./tempAA.java");
      FileInputStream inputBef = new FileInputStream(fileBef);
      InputStreamReader streamBef = new InputStreamReader(inputBef);
      brBef = new BufferedReader(streamBef);

      /* java�t�@�C����listBefore�Ɋi�[ */
      String lineBef;
      while((lineBef = brBef.readLine()) != null){
        listBefore.add(lineBef);
      }

      /* csv�t�@�C���̂P�s�ڂ�z��srt1[]�ɕ������Ȃ���i�[ */
      String[] key;
      key = listCsv.get(0).split(",");

      /* listBefore��listAfter�ɃR�s�[ */
      for (int j = 0 ; j < listBefore.size(); j++) {
        listAfter.add(listBefore.get(j));
      }


      for (int i = 1 ; i < listCsv.size(); i++) {
        String[] value;            
        value = listCsv.get(i).split(",");   //value[]��csv�t�@�C����i�s�ڂ𕪊����Ȃ���i�[

        for (int k = 0 ; k < listBefore.size(); k++) {
          listAfter.set(k,listBefore.get(k));  //
          String strBefore = listAfter.get(k);

          for(int l = 0 ; l < key.length; l++){
            if (-1 < strBefore.indexOf(key[l])) {           
              listAfter.set(k,strBefore.replace(key[l], value[l]));   
            } 
          }
        }
        for (int j = 0 ; j < listAfter.size(); j++) {
          File newFile = new File("./quiz/" + value[0] + ".java");
          newFile.createNewFile();

          FileWriter fw = new FileWriter("./quiz/" + value[0] + ".java");
          PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
            
          for (int m = 0 ; m < listAfter.size(); m++) {
            String changedLine = listAfter.get(m);
            pw.println(changedLine);
          }
          pw.close();
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        brCsv.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}