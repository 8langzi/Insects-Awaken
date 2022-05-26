package com.insects.getdata.service.securitiesassociationofchina;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.insects.getdata.common.HttpCommonUtils;
import com.insects.getdata.domain.Company;
import com.insects.getdata.domain.Employee;
import com.insects.getdata.service.baseServiceImpl.CompanyServiceImpl;
import com.insects.getdata.service.baseServiceImpl.EmployeeServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecuritiesAssociationOfChinaServiceImpl {

    @Autowired
    CompanyServiceImpl companyService;

    @Autowired
    EmployeeServiceImpl employeeService;

    private static X509TrustManager tm = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0,
                                       String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0,
                                       String arg1) throws CertificateException {
        }
    };

    // 获取公司数据的url
    String companyUrl = "https://exam.sac.net.cn/pages/registration/train-line-register!orderSearch.action";

    String employeeUrl = "https://exam.sac.net.cn/pages/registration/train-line-register!list.action";

    public void processAcquiredCompanyData() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, new TrustManager[]{tm}, null);
        HttpClient httpClient = HttpClients.custom().setSSLContext(ctx).build();
        HttpPost httpPost = new HttpPost(companyUrl);
        HttpCommonUtils.setHeader(httpPost);
        httpPost.setEntity(new UrlEncodedFormEntity(convertReq()));
        // 执行post请求
        HttpResponse response = httpClient.execute(httpPost);
        JSONArray companyArray = HttpCommonUtils.getJSONArrayResponse(response);
        // 转换公司数据并插入数据库
        convertCompanyDataAndinsertToDB(companyArray);
    }

    public void processAcquiredEmployeeData() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        List<String> allIds = companyService.getAllAOIID();
        allIds.parallelStream().forEach(id -> {
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(null, new TrustManager[]{tm}, null);
                HttpClient httpClient = HttpClients.custom().setSSLContext(ctx).build();
                HttpPost httpPost = new HttpPost(employeeUrl);
                HttpCommonUtils.setHeader(httpPost);
                // 根据公司信息获取雇员信息并转换插入数据库
                convertEmployeeDataAndinsertToDBByCompanyData(id, httpClient, httpPost);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        });
    }



    public void convertCompanyDataAndinsertToDB(JSONArray jsonArray) {
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Company company = JSON.toJavaObject((JSON) jsonArray.getJSONObject(i), Company.class);
            companies.add(company);
            if ((i % 50 == 0 && i != 0) || i == jsonArray.size() - 1) {
                companyService.addCompany(companies);
                System.out.println(Thread.currentThread() + " ----- compaynies sise is : " + companies.size());
                companies.clear();
            }
        }
    }


    public void convertEmployeeDataAndinsertToDBByCompanyData(String id, HttpClient httpClient, HttpPost httpPost) {
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(convertEmployeeReq(id)));
            HttpResponse responseSon = httpClient.execute(httpPost);
            String resultSon = EntityUtils.toString(responseSon.getEntity(), "utf-8");
            JSONObject employJSON = JSON.parseObject(resultSon);
            JSONArray employArray = employJSON.getJSONArray("result");
            System.out.println("id is " + id + " , employArray size is " + employArray.size());
            List<Employee> employees = new ArrayList<>();
//            for (int i = 0; i < employArray.size(); i++) {
//                Employee employee = JSON.toJavaObject((JSON) employArray.getJSONObject(i), Employee.class);
//                employees.add(employee);
//                if((i % 50 == 0 && i != 0) || employArray.size()-1 == i){
//                    employeeService.addEmploee(employees);
//                    System.out.println(Thread.currentThread() + " ----- Employee size is : " + employees.size());
//                    employees.clear();
//                }
//            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<NameValuePair> convertReq() {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("filter_EQS_OTC_ID", "10"));
        nvps.add(new BasicNameValuePair("ORDERNAME", "AOI#AOI_NAME"));
        nvps.add(new BasicNameValuePair("ORDER", "ASC"));
        nvps.add(new BasicNameValuePair("sqlkey", "registration"));
        nvps.add(new BasicNameValuePair("sqlval", "SELECT_LINE_PERSON"));
        return nvps;
    }

    public List<NameValuePair> convertEmployeeReq(String aoiId) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("filter_EQS_AOI_ID", aoiId));
        nvps.add(new BasicNameValuePair("filter_EQS_PTI_ID", null));
        nvps.add(new BasicNameValuePair("page.searchFileName", "homepage"));
        nvps.add(new BasicNameValuePair("page.sqlKey", "PAGE_FINISH_PUBLICITY"));
        nvps.add(new BasicNameValuePair("page.sqlCKey", "SIZE_FINISH_PUBLICITY"));
        nvps.add(new BasicNameValuePair("_search", "SIZE_FINISH_PUBLICITY"));
        nvps.add(new BasicNameValuePair("nd", ""));
        nvps.add(new BasicNameValuePair("page.pageSize", "15000"));
        nvps.add(new BasicNameValuePair("page.pageNo", "1"));
        nvps.add(new BasicNameValuePair("page.orderBy", "id"));
        nvps.add(new BasicNameValuePair("page.order", "desc"));
        return nvps;
    }
}
