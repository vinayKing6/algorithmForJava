package algorithm.problems.Search.BookManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import algorithm.problems.Search.RedBlackTree;
import algorithm.problems.Search.Set;

/**
 * BookManager
 */
public class BookManager {

    private static String[] config={"settings","filepaths","classifiers","authors","searchMap"};
    private RedBlackTree<String,RedBlackTree<String,String>> files;
    private RedBlackTree<String,RedBlackTree<String,Set<String>>> data;
    private static String splitor="%";
    private static String syspath="/media/vinay/Data/myFile/sharing_files/from_ubuntu/Java/src/algorithm/problems/Search/BookManager";
    public BookManager(){
        files=new RedBlackTree<String,RedBlackTree<String,String>>();
        data=new RedBlackTree<String,RedBlackTree<String,Set<String>>>();
        for (int i = 0; i < config.length; i++) {
            data.put(config[i], new RedBlackTree<String,Set<String>>());
        }
    }

    public static void init(String root,String repository){
        if (root.endsWith("/")) {
            root=root.substring(0, root.length()-1);
        }
        if (repository.endsWith("/")) {
            repository=repository.substring(0,repository.length()-1);
        }
        
        try{
            BufferedWriter writer=new BufferedWriter(
                    new FileWriter(syspath+"/"+config[0]+".txt"));
            writer.write("root%"+root+"\n");
            writer.write("repository%"+repository+"\n");
            writer.flush();
            writer.close();
        }catch(IOException ex){
            System.out.println("can not init.");
        }
    }

    public void save(){
        String path=syspath;
        //save files
        try{
            //files
            BufferedWriter writer=new BufferedWriter(
                    new FileWriter(path+"/"+"files.txt"));
            for (String filename : files.keys()) {
                writer.write(filename);
                var tree=files.get(filename);
                for (String feature : tree.keys()) {
                    writer.write("%"+feature+":"+tree.get(feature));
                }
                writer.write("\n");
            }
            writer.flush();
            writer.close();

            for (String feature : config) {
                writer=new BufferedWriter(new FileWriter(path+"/"+feature+".txt"));
                for(String key:data.get(feature).keys()){
                    writer.write(key);
                    var featureSet=data.get(feature).get(key);
                    if (featureSet!=null) {
                        for(String value:data.get(feature).get(key).keys()){
                            writer.write("%"+value);
                        }
                    }
                    writer.write("\n");
                }
                writer.flush();
                writer.close();
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void load(){
        try{
            File file;
            Scanner sc;
            //load data
            for (int i = 0; i < config.length; i++) {
                file=new File(syspath+"/"+config[i]+".txt");
                if(file.exists()){
                    RedBlackTree<String,Set<String>> current=data.get(config[i]);
                    sc=new Scanner(file);
                    while (sc.hasNextLine()) {
                        String[] line=sc.nextLine().split(splitor);
                        current.put(line[0], new Set<String>());
                        for (int j = 1; j < line.length; j++) {
                            current.get(line[0]).put(line[j]);
                        }
                    }
                }
            }

            //load files
            file=new File(syspath+"/files.txt");
            if (file.exists()) {
                sc=new Scanner(file);
                while (sc.hasNextLine()) {
                    String[] line=sc.nextLine().split(splitor);
                    files.put(line[0], new RedBlackTree<String,String>());
                    for (int i = 1; i < line.length; i++) {
                        String[] sets=line[i].split(":");
                        files.get(line[0]).put(sets[0], sets[1]);
                    }
                }
            }
            

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void add(String filename,String filepath,String classifier,String author){
        //put filename
        filepath=correctDir(filepath);
        var settings=data.get(config[0]);
        var root=settings.get("root").select(0);
        File dir=new File(root+"/"+filepath);
        if (files.contains(filename)) {
            //update
            var fileFeature=files.get(filename);
            String currentDir=fileFeature.get(config[1]);
            data.get(config[1]).get(currentDir).delete(filename);
            data.get(config[2]).get(fileFeature.get(config[2])).delete(filename);
            data.get(config[3]).get(fileFeature.get(config[3])).delete(filename);
            fileFeature.put(config[1],filepath );
            fileFeature.put(config[2], classifier);
            fileFeature.put(config[3], author);
            currentDir=root+"/"+currentDir+"/"+filename;
            String movingDir=root+"/"+filepath+"/"+filename;
            File file=new File(currentDir);
            //mkdirs
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.renameTo(new File(movingDir));
        }else{
            String currentDir=data.get(config[0]).get("repository").select(0)+"/"+filename;
            String movingDir=root+"/"+filepath+"/"+filename;
            File file=new File(currentDir);
            if (file.exists()==false) {
                System.out.println("the file is not found in repository");
                return;
            }
            files.put(filename, new RedBlackTree<String,String>());
            var fileFeature=files.get(filename);
            fileFeature.put(config[1], filepath);
            fileFeature.put(config[2], classifier);
            fileFeature.put(config[3], author);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            /* file.renameTo(new File(movingDir)); */
            try{
                InputStream in = new BufferedInputStream(
                        new FileInputStream(file));
                OutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(movingDir)));
                    byte[] buffer = new byte[1024];
                int lengthRead;
                while ((lengthRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, lengthRead);
                    out.flush();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
                
            if (!data.get(config[1]).contains(filepath)) {
                data.get(config[1]).put(filepath, new Set<String>());
                data.get(config[1]).get(filepath).put(filename);
            }else
                data.get(config[1]).get(filepath).put(filename);

            if (!data.get(config[2]).contains(classifier)) {
                data.get(config[2]).put(classifier, new Set<String>());
                data.get(config[2]).get(classifier).put(filename);
            }else
                data.get(config[2]).get(classifier).put(filename);

            if (!data.get(config[3]).contains(author)) {
                data.get(config[3]).put(author, new Set<String>());
                data.get(config[3]).get(author).put(filename);
            }else
                data.get(config[3]).get(author).put(filename);
        }
    }

    public void list(int option,String key){
        if (option!=-1) {
            String listConfig=config[option];
            var configTree=data.get(listConfig);
            var keySet=configTree.get(key);
            if (keySet!=null) {
                System.out.println(listConfig+": "+key);
                for (String string : keySet.keys()) {
                    System.out.println(string);
                }
            }else
                System.out.println(listConfig+" "+key+" is not found");
        }else{
            var listFile=files.get(key);
            if (listFile!=null) {
                System.out.println("filename: "+key);
                for(String attribute:listFile.keys()){
                    System.out.println(attribute+": "+listFile.get(attribute));
                }
            }
            else
                System.out.println("the file "+key+" is not found");
        }
        
    }

    public void search(String key){
        var searchMap=data.get(config[4]);
        var searchKeyMap=searchMap.get(key);
        var fileTree=files.get(key);
        if (fileTree!=null) {
            list(-1, key);
        }

        if (searchKeyMap!=null) {
            for(String Key:searchKeyMap.keys()){
                list(-1, Key);
            }

        }
        System.out.println("are you satisfied with our searh? enter y/n");
        Scanner sc=new Scanner(System.in);
        String option=sc.nextLine();
        if (option.equals("y")) {
            System.out.println("thank you!");
        }
        else{
            System.out.println("please enter your prefer!");
            option=sc.nextLine();
            if (files.get(option)!=null) {
                list(-1, option);
                if (searchKeyMap==null) {
                    searchMap.put(key, new Set<String>());
                    searchKeyMap=searchMap.get(key);
                }
                searchKeyMap.put(option);
                System.out.println("thank you for your result!\nhere is your result");
                list(-1, option);
            }else{
                System.out.println("there is no such file in root ,please add there!");
            }
        }
        return;
    }

    public String correctDir(String filepath){
        if (filepath.startsWith("/")) {
            filepath=filepath.substring(1);
        }
        if (filepath.endsWith("/")) {
            filepath=filepath.substring(0,filepath.length()-1);
        }
        return filepath;
    }

    public static void main(String[] args){
        String option;
        BookManager bm=new BookManager();
        bm.load();
        try{
            option=args[0];
        }catch(RuntimeException ex){
            System.out.println("no options!");
            return;
        }

        if (option.equals("-i")) {
            String root=args[1];
            String repository=args[2];
            BookManager.init(root,repository);
        }

        if (option.equals("-a")) {
            String filename=args[1];
            String filepath=args[2];
            String classifier=args[3];
            String author=args[4];
            bm.add(filename, filepath, classifier, author);
            bm.save();
        }

        if (option.equals("-l")) {
            String subOption=args[1];
            String key=args[2];

            if (subOption.equals("-p")) {
                bm.list(1,key);
            }else if (subOption.equals("-c")) {
                bm.list(2,key);
            }else if (subOption.equals("-a")) {
                bm.list(3, key);
            }else if (subOption.equals("-f")) {
                bm.list(-1, key);
            }
        }

        if (option.equals("-s")) {
            String subOption=args[1];
            bm.search(subOption);
            bm.save();
        }
    }
}
