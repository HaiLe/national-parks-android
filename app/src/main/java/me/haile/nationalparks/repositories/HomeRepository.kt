///*
// * Copyright 2020 Google LLC
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package me.haile.nationalparks.repositories
//
//import android.accounts.AccountManager
//import android.content.Context
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import dagger.hilt.android.qualifiers.ApplicationContext
//import kotlinx.coroutines.flow.Flow
//import me.haile.memor.R
//import me.haile.memor.api.NewsApiService
//import me.haile.memor.api.UnsplashService
//import me.haile.memor.utils.Logging
//import me.haile.nationalparks.api.NPSService
//import org.jsoup.Jsoup
//import org.jsoup.nodes.Document
//import org.jsoup.nodes.Element
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import javax.inject.Inject
//
//class HomeRepository @Inject constructor(
//    @ApplicationContext val context: Context,
//    //private val service: UnsplashService,
//    private val npsService: NPSService
//) {
//
////    fun getSearchResultStream(query: String): Flow<PagingData<UnsplashPhoto>> {
////        return Pager(
////            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
////            pagingSourceFactory = { UnsplashPagingSource(service, query) }
////        ).flow
////    }
//
////    fun loadArticlesFromJSON(): List<MemorArticle> {
////        val memorArticles: List<MemorArticle>
////        val inputStream = context.resources.openRawResource(R.raw.articles)
////        val reader = BufferedReader(InputStreamReader(inputStream))
////        val content = StringBuilder()
////        reader.use { r ->
////            var line: String? = r.readLine()
////            while (line != null) {
////                content.append(line)
////                line = r.readLine()
////            }
////        }
////
////        val gson = Gson()
////        val listType = object : TypeToken<List<MemorArticle>>() {}.type
////        memorArticles = gson.fromJson(content.toString(), listType)
////        return memorArticles
////    }
//
//    // crawl data from ny times by selecting Xpath elements
//    fun crawlFromNYTimes(url: String): List<MemorArticle> {
//        val memorArticles: MutableList<MemorArticle> = mutableListOf()
//
//        val document = Jsoup.connect(url).get()
//        //document.select("header, footer, nav, .ads").remove();
//        // Path of articles present in the web
//        val articleHTML = document.getElementById("stream-panel")
//            ?.select("div")?.select("ol")
//            ?.select("div")?.select("div")?.select("a")
//
//        // iterate each article to get content
//        articleHTML?.forEach { item ->
//            val image = item.select("div").select("figure")
//                .select("div").select("img").attr("src")
//            val title = item.select("h3").text()
//            val description = item.select("p").text()
//            val author = item.select("div").select("p").select("span").text()
//            val source = item.attr("href")
//            Logging.log("description: $description")
//
//            // check for null content
//            if (!image.isNullOrEmpty() || !title.isNullOrEmpty() ||
//                !description.isNullOrEmpty() || !author.isNullOrEmpty() ||
//                !source.isNullOrEmpty()
//            ) {
//                val memorArticle = MemorArticle(
//                    title = title,
//                    description = description,
//                    image = image,
//                    author = author,
//                    source = source
//                )
//                // add iterated articles to list
//                memorArticles.add(memorArticle)
//            }
//        }
//        return memorArticles
//    }
//
//    fun parseHTMLContent(url: String) : String {
//        val outputSettings = Document.OutputSettings()
//        outputSettings.prettyPrint(false)
//        val document: Document = Jsoup.connect(url).get()
//
//        // Handling line breaks and paragraphs
//        document.select("br").append("\\n");
//        document.select("p").before("\\n").after("\\n");
//
//        document.select("header, footer, nav, .ads").remove();
//        var mainContent: Element? = document.select("article, .article-content, articleBody").first()
//
//        if (mainContent == null) {
//            mainContent = document.select("div[role=main]").first(); // Fallback
//        }
//        val text = mainContent?.text() ?: ""
//        val extractedText = mainContent?.text()?.replace("\\n", "\n")
//
//        val body = document.body()
//        val articleBody = document.getElementById("articleBody")
//        val title: String? = document.select("td ul li b").first()?.text()
//        val content: String? = document.select("td ul li").first()?.ownText()
//        val linkEl: Element? = document.select("td ul li p a").first()
//        val href: String? = linkEl?.attr("href")
//        val link: String? = linkEl?.text()
//
//        Logging.log("page details parsing")
//        Logging.log("text: $extractedText")
////        Logging.log("title: $title")
////        Logging.log("content: $content")
////        Logging.log("linkEl: $linkEl")
////        Logging.log("href: $href")
////        Logging.log("link: $link")
//
//        return extractedText?: ""
//    }
//
//    fun initPocketAPI() {
//        val consumerKey = "104188-de70d72f3b4ac741865fc9a"
//        val am: AccountManager = AccountManager.get(context)
////        val options = Bundle()
////        am.getAuthToken(
////            myAccount_,                     // Account retrieved using getAccountsByType()
////            "Manage your tasks",            // Auth scope
////            options,                        // Authenticator-specific options
////            this,                           // Your activity
////            OnTokenAcquired(),              // Callback called when a token is successfully acquired
////            Handler(OnError())              // Callback called if an error occurs
////        )
//    }
//
//
//    companion object {
//        private const val NETWORK_PAGE_SIZE = 25
//    }
//}
