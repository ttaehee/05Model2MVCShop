package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		
		product.setProdName("testProductName");
		product.setProdDetail("testProductDetail");
		product.setManuDate("testDate");
		product.setPrice(2000);
		product.setFileName("testImage");
		
		productService.addProduct(product);
		
		//product= productService.getProduct("testUserId");

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals("testDate", product.getManuDate());
		Assert.assertEquals(2000, product.getPrice());
		Assert.assertEquals("testImage", product.getFileName());
	}
	
	//@Test
    public void testGetProduct() throws Exception {
		
		Product product = new Product();

		product = productService.getProduct(10009);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals("testDate", product.getManuDate());
		Assert.assertEquals(2000, product.getPrice());
		Assert.assertEquals("testImage", product.getFileName());

		//Assert.assertNotNull(userService.getUser("user02"));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		 Product product= productService.getProduct(10009);
		Assert.assertNotNull(product);

		product.setProdName("updateName");
		product.setProdDetail("updateDetail");
		product.setManuDate("upDate");
		product.setPrice(1111);
		product.setFileName("updateImage");
		
		productService.updateProduct(product);
		
		product= productService.getProduct(10009);
		Assert.assertNotNull(product);
		
		//==> console 확인
		System.out.println(product);
			
		//==> API 확인
		Assert.assertEquals("updateName", product.getProdName());
		Assert.assertEquals("updateDetail", product.getProdDetail());
		Assert.assertEquals("upDate", product.getManuDate());
		Assert.assertEquals(1111, product.getPrice());
		Assert.assertEquals("updateImage", product.getFileName());
	 }

	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println("list:"+list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount:"+totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }

	// @Test
	 public void testGetProductListProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10009");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println("list:"+list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println("list:"+list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }

	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("updateName");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 }	 

}