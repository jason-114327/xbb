package cn.itcast;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestSolr {

    @Test
    public void testSolrJ() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://8.129.42.244:8080/solr/");

        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id", 3);
        doc.setField("name", "张三");

        solrServer.add(doc);

        solrServer.commit();
    }

    //Solr服务器的Java接口查询
    @Test
    public void testQuery() throws Exception {
        //连接服务器的请求路径
        String baseURL = "http://8.129.42.244:8080/solr/";
        //创建Solr的客户端  连接Solr服务器
        SolrServer solrServer = new HttpSolrServer(baseURL);
        //查询
        SolrQuery params = new SolrQuery();
        //关键词
        params.set("q", "*:*");
        //执行查询  返回查询结果集
        QueryResponse response = solrServer.query(params);
        //获取结果集
        SolrDocumentList docs = response.getResults();
        //取出结果的总条数
        long numFound = docs.getNumFound();
        System.out.println("总条数：" + numFound);
        //结果集遍历
        for (SolrDocument doc : docs) {
            //获取ID
            String id = (String) doc.get("id");
            //获取标题
            String name = (String) doc.get("name");
            System.out.println(id);
            System.out.println(name);
        }
    }

    @Autowired
    private SolrServer solrServer;

    // 通过spring 容器获取
    @Test
    public void testSolrJSpring() throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id", 4);
        doc.setField("name", "李四");

        solrServer.add(doc);

        solrServer.commit();
    }
}
