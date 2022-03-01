package com.itheima.heima_mm_serach.listener;

import com.alibaba.fastjson.JSON;
import com.itheima.common.contants.RabbitMQConstants;
import com.itheima.heima_mm_serach.mapper.QuestionMapper;
import com.itheima.question.doc.QuestionDoc;
import com.itheima.question.pojo.Question;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Load2ESListener {
    @Autowired
    private QuestionMapper questionMapper;

    private RestHighLevelClient restHighLevelClient;
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = RabbitMQConstants.QUEUE_ES),
//            exchange = @Exchange(name = RabbitMQConstants.EXCHANGE_QUESTION,type = ExchangeTypes.TOPIC),
//            key = { RabbitMQConstants.ROUTING_KEY_ES }
//    ))
    public void loadQuestionToEs(String questId) throws IOException {
        this.restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://10.54.27.203:9200")
        ));
        System.out.println("导入问题到索引库："+questId);
        List<Question> questions=questionMapper.selectList(null);
        BulkRequest request=new BulkRequest();
        for (Question question:questions)
        {
            QuestionDoc questionDoc=new QuestionDoc(question);
            request.add(new IndexRequest("hotel").id(questionDoc.getId()).source(JSON.toJSONString(questionDoc), XContentType.JSON));
        }
        this.restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        this.restHighLevelClient.close();
        }
        //TODO  从数据库查询数据【 调用 heima_mm_question的服务】
        //TODO  调用es的api导入索引库
}
