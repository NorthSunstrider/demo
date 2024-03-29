package com.ns.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * @author North
 * @date 2022/08/21
 */
public class TestLucene {
    String indexDirectory = "C:\\Matrix\\Lucene\\index";
    String sourceDirectory = "C:\\Matrix\\Lucene\\source";

    @Test
    public void testMatchAllDocsQuery() throws Exception {
        // 创建一个Directory对象，指定索引库存放的路径
        Directory directory = FSDirectory.open(new File(indexDirectory));
        // 创建IndexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建Indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 创建查询条件
        // 使用MatchAllDocsQuery查询索引目录中的所有文档
        Query query = new MatchAllDocsQuery();
        // 执行查询
        // 第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);

        // 查询结果的总条数
        System.out.println("查询结果的总条数：" + topDocs.totalHits);

        Assertions.assertTrue(topDocs.totalHits > 0);

        // 遍历查询结果
        // topDocs.scoreDocs存储了document对象的id
        // ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // scoreDoc.doc属性就是document对象的id
            // int doc = scoreDoc.doc;
            // 根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            // 文件名称
            System.out.println(document.get("fileName"));
            // 文件内容
            System.out.println(document.get("fileContent"));
            // 文件大小
            System.out.println(document.get("fileSize"));
            // 文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        // 关闭indexreader对象
        indexReader.close();
    }

    // 搜索索引
    @Test
    public void testSearchIndex() throws IOException {
        // 创建一个Directory对象，指定索引库存放的路径
        Directory directory = FSDirectory.open(new File(indexDirectory));
        // 创建IndexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建Indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 创建一个TermQuery（精准查询）对象，指定查询的域与查询的关键词
        // 创建查询
        Query query = new TermQuery(new Term("fileContent", "长"));
        // 执行查询
        // 第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);

        Assertions.assertTrue(topDocs.totalHits > 0);

        // 查询结果的总条数
        System.out.println("查询结果的总条数：" + topDocs.totalHits);
        // 遍历查询结果
        // topDocs.scoreDocs存储了document对象的id
        // ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // scoreDoc.doc属性就是document对象的id
            // int doc = scoreDoc.doc;
            // 根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            // 文件名称
            System.out.println(document.get("fileName"));
            // 文件内容
            System.out.println(document.get("fileContent"));
            // 文件大小
            System.out.println(document.get("fileSize"));
            // 文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        // 关闭indexreader对象
        indexReader.close();
    }

    // 数值范围查询
    @Test
    public void testNumericRangeQuery() throws Exception {
        // 创建一个Directory对象，指定索引库存放的路径
        Directory directory = FSDirectory.open(new File(indexDirectory));
        // 创建IndexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建Indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 创建查询
        // 参数：
        // 1.域名
        // 2.最小值
        // 3.最大值
        // 4.是否包含最小值
        // 5.是否包含最大值
        Query query = NumericRangeQuery.newLongRange("fileSize", 41L, 2055L, true, true);
        // 执行查询

        // 第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);

        // 查询结果的总条数
        System.out.println("查询结果的总条数：" + topDocs.totalHits);
        // 遍历查询结果
        // topDocs.scoreDocs存储了document对象的id
        // ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // scoreDoc.doc属性就是document对象的id
            // int doc = scoreDoc.doc;
            // 根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            // 文件名称
            System.out.println(document.get("fileName"));
            // 文件内容
            System.out.println(document.get("fileContent"));
            // 文件大小
            System.out.println(document.get("fileSize"));
            // 文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        // 关闭indexreader对象
        indexReader.close();
    }

    // 组合条件查询
    @Test
    public void testBooleanQuery() throws Exception {
        // 创建一个Directory对象，指定索引库存放的路径
        Directory directory = FSDirectory.open(new File(indexDirectory));
        // 创建IndexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建Indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 创建一个布尔查询对象
        BooleanQuery query = new BooleanQuery();
        // 创建第一个查询条件
        Query query1 = new TermQuery(new Term("fileName", "apache"));
        Query query2 = new TermQuery(new Term("fileName", "lucene"));
        // 组合查询条件
        query.add(query1, Occur.MUST);
        query.add(query2, Occur.MUST);
        // 执行查询

        // 第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);

        // 查询结果的总条数
        System.out.println("查询结果的总条数：" + topDocs.totalHits);
        // 遍历查询结果
        // topDocs.scoreDocs存储了document对象的id
        // ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // scoreDoc.doc属性就是document对象的id
            // int doc = scoreDoc.doc;
            // 根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            // 文件名称
            System.out.println(document.get("fileName"));
            // 文件内容
            System.out.println(document.get("fileContent"));
            // 文件大小
            System.out.println(document.get("fileSize"));
            // 文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        // 关闭indexreader对象
        indexReader.close();
    }

    @Test
    public void testQueryParser() throws Exception {
        // 创建一个Directory对象，指定索引库存放的路径
        Directory directory = FSDirectory.open(new File(indexDirectory));
        // 创建IndexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建Indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 创建queryparser对象
        // 第一个参数默认搜索的域
        // 第二个参数就是分析器对象
        QueryParser queryParser = new QueryParser("fileContent", new IKAnalyzer());
        // 使用默认的域,这里用的是语法，下面会详细讲解一下
        Query query = queryParser.parse("长沙");
        // 不使用默认的域，可以自己指定域
        // Query query = queryParser.parse("fileContent:apache");
        // 执行查询

        // 第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);

        // 查询结果的总条数
        System.out.println("查询结果的总条数：" + topDocs.totalHits);
        // 遍历查询结果
        // topDocs.scoreDocs存储了document对象的id
        // ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // scoreDoc.doc属性就是document对象的id
            // int doc = scoreDoc.doc;
            // 根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            // 文件名称
            System.out.println(document.get("fileName"));
            // 文件内容
            System.out.println(document.get("fileContent"));
            // 文件大小
            System.out.println(document.get("fileSize"));
            // 文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        // 关闭indexreader对象
        indexReader.close();
    }

    @Test
    public void testMultiFiledQueryParser() throws Exception {
        // 创建一个Directory对象，指定索引库存放的路径
        Directory directory = FSDirectory.open(new File("E:\\programme\\test"));
        // 创建IndexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建Indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 可以指定默认搜索的域是多个
        String[] fields = {"fileName", "fileContent"};
        // 创建一个MulitFiledQueryParser对象
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, new IKAnalyzer());
        Query query = queryParser.parse("apache");
        System.out.println(query);
        // 执行查询

        // 第一个参数是查询对象，第二个参数是查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);

        // 查询结果的总条数
        System.out.println("查询结果的总条数：" + topDocs.totalHits);
        // 遍历查询结果
        // topDocs.scoreDocs存储了document对象的id
        // ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // scoreDoc.doc属性就是document对象的id
            // int doc = scoreDoc.doc;
            // 根据document的id找到document对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            // 文件名称
            System.out.println(document.get("fileName"));
            // 文件内容
            System.out.println(document.get("fileContent"));
            // 文件大小
            System.out.println(document.get("fileSize"));
            // 文件路径
            System.out.println(document.get("filePath"));
            System.out.println("----------------------------------");
        }
        // 关闭indexreader对象
        indexReader.close();
    }

    // 删除全部索引
    @Test
    public void testDeleteAllIndex() throws Exception {
        Directory directory = FSDirectory.open(new File(indexDirectory));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 删除全部索引
        indexWriter.deleteAll();
        // 关闭indexwriter
        indexWriter.close();
    }

    // 根据查询条件删除索引
    @Test
    public void deleteIndexByQuery() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\programme\\test"));
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 创建一个查询条件
        Query query = new TermQuery(new Term("fileContent", "apache"));
        // 根据查询条件删除
        indexWriter.deleteDocuments(query);
        // 关闭indexwriter
        indexWriter.close();
    }

    // 修改索引库
    @Test
    public void updateIndex() throws Exception {
        Directory directory = FSDirectory.open(new File("E:\\programme\\test"));
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 创建一个Document对象
        Document document = new Document();
        // 向document对象中添加域。
        // 不同的document可以有不同的域，同一个document可以有相同的域。
        document.add(new TextField("fileXXX", "要更新的文档", Store.YES));
        document.add(new TextField("contentYYY", "简介 Lucene 是一个基于 Java 的全文信息检索工具包。", Store.YES));
        indexWriter.updateDocument(new Term("fileName", "apache"), document);
        // 关闭indexWriter
        indexWriter.close();
    }
}
