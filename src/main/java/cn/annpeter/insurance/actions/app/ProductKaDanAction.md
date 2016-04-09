##卡单模块接口交流

###请求查看所有卡单(/app/kadan/list)
* 返回json样例

	```
		{
          "result": [
            {
              "description": "产品描述",
              "id": 14,
              "title": "产品名称",
              "img_small": "http://localhost:8080/static/admin/upload/2016/MAR/30/48471747_5876M.jpg",
              "price": 213
            },
            {
              "description": "desc19",
              "id": 17,
              "title": "1239",
              "img_small": "http://localhost:8080/static/admin/upload/2016/APR/06/11840506_759Z2.jpg",
              "price": 12319
            }
          ],
          "message": "请求卡单列表成功",
          "status": 1
        }
	```

###请求查看某条卡单具体信息(/app/kadan/profile?id=productID)
* 请求携带productID(产品id，所有产品共享的id字段)
* 返回json样例

	```
	{
      "result": [
        {
          "productTitle": "产品名称",
          "productId": 1,
          "kadanConditions": "出生28日以上、6周岁（不含）以下、身体健康的儿童",
          "kadanHtmlInfo": "<p>sadfaf</p>",
          "kadanDescription": "平安鸿运随行系列",
          "kadanImgSmall": "http://192.168.1.247:8080/static/admin/upload/2016/APR/07/47961397_3UL28.png",
          "supplierTitle": "新华保险",
          "kadanPrice": 123,
          "productSummary": "产品描述",
          "productCateName": "产品类别"
        }
      ],
      "message": "请求卡单成功",
      "status": 1
    }
	```
