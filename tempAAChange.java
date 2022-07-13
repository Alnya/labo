import java.util.*;
import java.io.*;

public class tempAAChange{
  public static void main(String[] args) {
    List<String> listCsv = new ArrayList<>();
    List<String> listBefore = new ArrayList<>();

    BufferedReader brCsv = null;
    BufferedReader brBef;

    try {
      /* csv�t�@�C���Ǎ� */
      File file = new File("./tempA.csv");
      FileInputStream input = new FileInputStream(file);
      InputStreamReader stream = new InputStreamReader(input);
      brCsv = new BufferedReader(stream);

      /* csv�t�@�C����listCsv�Ɋi�[ */
      String line;
      while((line = brCsv.readLine()) != null){  //�h1�s�ǂݍ���Ŋi�[�h���J��Ԃ�
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

      /* csv�t�@�C���̂P�s�ڂ�z��key[]�ɕ������Ȃ���i�[ */
      String[] key;
      key = listCsv.get(0).split(",");

      /* �V�K�t�H���_ "key[0]" ���쐬 */
      File newfolder = new File("./" + key[0]);
      if (newfolder.mkdir()){
          System.out.println("make directory: ./" + key[0]);
      }

      for (int i = 1; i < listCsv.size(); i++) {
        File newFile = new File("./" + key[0] + "/" + listCsv.get(i).split(",")[0] + ".java");
        if (newFile.createNewFile()){
            System.out.println("make file: ./" + key[0] + "/" + listCsv.get(i).split(",")[0] + ".java");
        }
      }

      /* listBefore��listAfter�ɃR�s�[ */
      List<String> listAfter = new ArrayList<>(listBefore);

      List<List<String>> values = new ArrayList<>();

      /* value1�s�ڂ���ŏI�s�܂ŌJ��Ԃ� */
      for (int j = 1 ; j < listCsv.size(); j++) {
        String[] value;
        value = listCsv.get(j).split(",");   //value[]��csv�t�@�C����i�s�ڂ𕪊����Ȃ���i�[

        /* �e���v���t�@�C����1�s�ڂ���ŏI�s�ڂ܂ŌJ��Ԃ� */
        for (int k = 0 ; k < listBefore.size(); k++) {
          listAfter.set(k,listBefore.get(k));
          String strBefore = listAfter.get(k);//set���g�����ƂŁAlistAfter�ɓ����Ă���ЂƂO�̍������Z�b�g
                                              //add�ɂ����1��ڂ͂ł��邯��2��ڈȍ~�O��̖�肪�p������Ă��܂�

          /* key�z��̐����J��Ԃ� */
          for(int l = 0 ; l < key.length; l++){
            if (strBefore.contains(key[l])) {                   //����key[l]����������
              listAfter.set(k,strBefore.replace(key[l], value[l])); //key[l]��value[l]�����ւ��Ă�����xset
            }
          }
        }
        /* ���̎��_��listAfter�ɕ�����u����̐V������肪���� */

        /* �V�K�t�H���_�[ �hkey[0]�h �ɐV�����t�@�C�� "value[0].java" ���쐬 */
//        File newFile = new File("./" + key[0] + "/" + value[0] + ".java");
//        newFile.createNewFile();

        /* �V�K�t�@�C���ɏ������ޏ��� */
        FileWriter fw = new FileWriter("./" + key[0] + "/" + value[0] + ".java");
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

        /* listAfter��V�K�t�@�C���ɏ������݁I */
        for (String changedLine : listAfter) {
          pw.println(changedLine);
        }
        pw.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        brCsv.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}