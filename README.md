1.此專案為Kotlin撰寫
  主頁以載入Fragment方式,Fragment採用客製化recyclerView加載資料; 切換語言採用Toolbar客製化,按下後可以切換語言
  recyclerView採用ConcatAdapter方式,將兩個不同的adpter資料(最新消息,遊憩景點)串在一起

2.當點擊最新消息
  採用webview載入對應的url
  (當主頁選擇其他語言,此頁也會對應所選擇的語言)
  按下Toolbar返回建,可回到主頁

3.當點擊遊憩景點
  採用客製化recyclerView 呈現title,景點詳細內容,圖片,官方網站
  (當主頁選擇其他語言,此頁也會對應所選擇的語言)
  當點擊官方網站網址
  可前往webview瀏覽該官方網站 
  按下Toolbar返回建,可回到主頁
