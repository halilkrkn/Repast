# Repast

- Devnot-Innova Android Kotlin Bootcamp Bitirme Projesi olup Kasım Adalan Tarafından verilmiş olan api içerisindeki verileri çekip listeleyip ve post işlemi yaparak api'ye kullanıcı tarafından istekler gönderildi. 
- Yani bu uygulama Yemeklerin içerisinde olduğu api üzerinden get işlemi ile çekip uygulama içerisinde gösterildi ve kullanıcı ile post işlemleri yaptırıldı.
- Uygulama içerisinde NavigationBottomBar sayesinde sayfalar arasında geçişler sağlandı.
- Uygulamada basit bir giriş sayfası mevcut ve bu sayfa ile Jetpack DataStore kullanılarak kullanıcı adı ile yemeklerin listelendiği alana yöneldiriliyor.
- Yemekler apiden çekilip yemek listesi olarak kullanıcıya ürünler gözüküyor
- Herbir yemeğin detayına ulaşılıp içerisinde ürünün adedini arttırıp-azaltabiliyoruz sonra ise apiye post işlemi yaparak apiye gönderiyoruz ve kullanıcı adına göre yemekleri sepete ekletiyoruz.
- Yemek sepetinde apiye post olarak gönderdiğimiz yemekleri  kullanıcı adına özgü şekilde listeliyoruz ve yemeği sepetten silebiliyoruz.
- Silinen yemekler apidenden sepetteki yemek id ve kullanıcı adına göre siliniyor.
- Room Database kullanılarak yemeklerin listelendiği sayfada favorilere eklenen ürünleri Favori Sayfasında listeleme, filtreleme ve silme işlemlerini yapabiliyoruz.


- ## Bu Uygulamada Android Geliştirme için İleri Seviye Teknolojiler kullanılmıştır.
- ### Kullanılan Teknolojiler:
- MVVM Architecture
- Dagger Hilt
- Retrofit
- Flow
- Jetpack DataStore
- Lifecycle
- Fragments
- Glide
- ViewBinding
- Databinding
- Navigation Components
- Coroutines 
- ## Bu Uygulamada Neler Öğreneceksiniz??
- MVVM mimarisini kullanımı
- Yemeklerin verilmiş olduğu api üzerinden verileri retroit kütüphanesindeki yapıları kullanarak daha sağlıklı nasıl get işlemi ile çekildiğini ve nasıl post işlemi ile apiye veri gönderildiğini
- Recyclerview yapısı sayesinde verileri listelemeyi
- Databinding ve ViewBinding implementasyonu ve kullanımı
- Jetpack DataStore yapısının nasıl kullanıldığını 
- LiveData yapısının nasıl kullanıldığını
- Navigation Componentlerinin nasıl kullanılması gerektiğini
- Glide kütüphanesinin nasıl kullanılması gerektiğini
- Dagger Hilt sayesinde modeller ve View arasındaki bağlantıları, Application içerisindeki bağlantıları, Retrofitle olan bağlantıların nasıl oluşturulduğunu 
- Recyclerview içerisinde DiffUtil kullanımını 
- Room Database kullanılarak nasıl verilerin database e eklendiğini, silindiğini, listelendiğini, filtrelendiğini
- Kotlin Flow yapısının database içerisinde kullanıp ViewModel içerisinde ise nasıl kullanılması gerektiği ve View e nasıl aktarılması gerektiğini öğreneceksiniz.
## [ScreenShots](https://github.com/halilkrkn/Repast/tree/master/screenshots)

![Alt Text](https://github.com/halilkrkn/Repast/blob/master/screenshots/repast_delivery.gif)

