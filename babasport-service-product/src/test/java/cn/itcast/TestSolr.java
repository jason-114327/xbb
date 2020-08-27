package cn.itcast;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
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
        SolrServer solrServer = new HttpSolrServer("http://192.168.200.128:8080/solr/");

        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id",3);
        doc.setField("name","张三");

        solrServer.add(doc);

        solrServer.commit();
    }

    @Autowired
    private SolrServer solrServer;
    // 通过spring 容器获取
    @Test
    public void testSolrJSpring() throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id",4);
        doc.setField("name","李四");

        solrServer.add(doc);

        solrServer.commit();
    }
}
