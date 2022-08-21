#基于Lucene4.10.3的示例项目
Lucene已经更新到9.3.0
Lucene是一个全文搜索引擎框架，基于lucene的还有solr和elasticsearch
这个项目引入了lucene，为了文件处理引入commons-io，为了分词处理引入google的ik_analyzer，不过ik analyzer 2012就停止更新了

##IK Analyzer
可以定义extend.dict stopword.dict IKAnalyzer.cfg.xml
扩展词典：为的是让需要切分的字符串的词语 根据扩展词典里的词，不要切分开来。
停止词典：对比停止词典，直接删掉停止词典中出现的词语

项目先运行TestA，用以读取源文件，并在指定位置生成索引文件
运行TestLucene中的方法，通常为指定索引文件位置，然后读取索引文件并根据条件查询