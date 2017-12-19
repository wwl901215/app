package com.wwl.can.learn.bean;

import java.util.List;

/**
 * Created by wangwenliang on 2017/12/19.
 */

public class BookMessage {
    /**
     * count : 1
     * start : 0
     * total : 1063
     * books : [{"rating":{"max":10,"numRaters":734,"average":"8.7","min":0},"subtitle":"","author":["[美] 菲利普·迪克"],"pubdate":"2017-10","tags":[{"count":397,"name":"科幻","title":"科幻"},{"count":331,"name":"科幻小说","title":"科幻小说"},{"count":294,"name":"菲利普·迪克","title":"菲利普·迪克"},{"count":218,"name":"人工智能","title":"人工智能"},{"count":171,"name":"小说","title":"小说"},{"count":139,"name":"美国","title":"美国"},{"count":120,"name":"外国文学","title":"外国文学"},{"count":101,"name":"美国文学","title":"美国文学"}],"origin_title":"Do Androids Dream of Electric Sheep?","image":"https://img3.doubanio.com/mpic/s29578434.jpg","binding":"精装","translator":["许东华"],"catalog":"","ebook_url":"https://read.douban.com/ebook/40602086/","pages":"267","images":{"small":"https://img3.doubanio.com/spic/s29578434.jpg","large":"https://img3.doubanio.com/lpic/s29578434.jpg","medium":"https://img3.doubanio.com/mpic/s29578434.jpg"},"alt":"https://book.douban.com/subject/27041533/","id":"27041533","publisher":"译林出版社","isbn10":"7544756424","isbn13":"9787544756426","title":"仿生人会梦见电子羊吗？","url":"https://api.douban.com/v2/book/27041533","alt_title":"Do Androids Dream of Electric Sheep?","author_intro":"菲利普\u2022迪克（Philip K. Dick），美国科幻文学界的传奇人物，共出版44部长篇小说和121篇短篇小说，曾获雨果奖和坎贝尔奖。作品集中探讨\u201c何为真实\u201d以及\u201c个体身份建构\u201d。代表作有《少数派报告》《尤比克》《仿生人会梦见电子羊吗？》《高堡奇人》《流吧！我的眼泪》等。盛名经久不衰，有多部作品被改编成电影，包括《银翼杀手》《少数派报告》《全面回忆》等，一再催生票房新高。以其名字命名的菲利普\u2022K.迪克奖是美国科幻界的主要奖项之一。","summary":"\u201c科幻鬼才\u201d菲利普\u2022迪克成长于西方科技文明创造出的崭新辉煌的时代。彼时，人类进入了太空，登上了月球，成功制造出第一台工业用机器人\u2026\u2026科技的蓬勃发展也催生出主流科幻小说对人类创造力的无比自信，克拉克、阿西莫夫和海因莱因撑起了西方科幻的黄金时代。可是，迪克却反其道而行之，他的主人公迷惘于亦真亦假的世界上，挣扎于文明的陷落中，充满了对生命的依恋和对人性的追求。\n《仿生人会梦见电子羊吗？》是菲利普\u2022迪克最负盛名的作品，小说描述了从一天早晨到第二天早晨的二十多个小时，主人公里克\u2022德卡德为了赏金追杀几个仿生人的过程，历经无数变故，狗血共桃花一色，阴谋与暴力齐飞。小说里的人类受到地球辐射尘的影响，相貌丑陋，很多在心智上都发生了退化，而仿生人却外表堂堂，多才多艺。当仿生人在外表和心智上无限逼近人类，甚至表面上已经超越人类时，人究竟何以为人？\n1982年，菲利普\u2022迪克去世后不久，根据《仿生人会梦见电子羊吗？》改编的电影《银翼杀手》上映，雷德利\u2022斯科特导演。电影笼罩着一种迷离、朦胧、似幻似真的基调，描绘了一个末世大都市洛杉矶，是后世无数赛博朋克电影的鼻祖之作。我是谁？我从哪里来？要到哪里去？对这个哲学终极命题的探讨，让这部电影成为科幻电影史上的经典之作，也赋予其历久弥新的魅力。\n2017年，电影续集《银翼杀手2049》在北美定档10月6日，也即将与中国观众见面。关于生存和生命的意义，我们永远没有确切答案。\n核战后，放射尘让地球上的动物濒临灭绝，地球已不再适合人类居住。为了鼓励残存的人口移民，政府承诺，只要移民到外星球，就为每人自动配备一个仿生人帮助其生活。仿生人不满足于被人类奴役的现状，想方设法逃回地球。\n小说主人公里克\u2022德卡德是一名专门追捕逃亡仿生人的赏金猎人。在一次追捕行动中，里克遭遇了新型仿生人前所未有的挑战。九死之后，能否一生？在与仿生人的接触和较量中，里克发现自己对仿生人的看法和态度有了很大的改变。这种改变究竟是福还是祸？","ebook_price":"20.00","series":{"id":"38198","title":"菲利普·迪克作品（精装）"},"price":"39.80"}]
     */

    private int count;
    private int start;
    private int total;
    private List<BooksBean> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        /**
         * rating : {"max":10,"numRaters":734,"average":"8.7","min":0}
         * subtitle :
         * author : ["[美] 菲利普·迪克"]
         * pubdate : 2017-10
         * tags : [{"count":397,"name":"科幻","title":"科幻"},{"count":331,"name":"科幻小说","title":"科幻小说"},{"count":294,"name":"菲利普·迪克","title":"菲利普·迪克"},{"count":218,"name":"人工智能","title":"人工智能"},{"count":171,"name":"小说","title":"小说"},{"count":139,"name":"美国","title":"美国"},{"count":120,"name":"外国文学","title":"外国文学"},{"count":101,"name":"美国文学","title":"美国文学"}]
         * origin_title : Do Androids Dream of Electric Sheep?
         * image : https://img3.doubanio.com/mpic/s29578434.jpg
         * binding : 精装
         * translator : ["许东华"]
         * catalog :
         * ebook_url : https://read.douban.com/ebook/40602086/
         * pages : 267
         * images : {"small":"https://img3.doubanio.com/spic/s29578434.jpg","large":"https://img3.doubanio.com/lpic/s29578434.jpg","medium":"https://img3.doubanio.com/mpic/s29578434.jpg"}
         * alt : https://book.douban.com/subject/27041533/
         * id : 27041533
         * publisher : 译林出版社
         * isbn10 : 7544756424
         * isbn13 : 9787544756426
         * title : 仿生人会梦见电子羊吗？
         * url : https://api.douban.com/v2/book/27041533
         * alt_title : Do Androids Dream of Electric Sheep?
         * author_intro : 菲利普•迪克（Philip K. Dick），美国科幻文学界的传奇人物，共出版44部长篇小说和121篇短篇小说，曾获雨果奖和坎贝尔奖。作品集中探讨“何为真实”以及“个体身份建构”。代表作有《少数派报告》《尤比克》《仿生人会梦见电子羊吗？》《高堡奇人》《流吧！我的眼泪》等。盛名经久不衰，有多部作品被改编成电影，包括《银翼杀手》《少数派报告》《全面回忆》等，一再催生票房新高。以其名字命名的菲利普•K.迪克奖是美国科幻界的主要奖项之一。
         * summary : “科幻鬼才”菲利普•迪克成长于西方科技文明创造出的崭新辉煌的时代。彼时，人类进入了太空，登上了月球，成功制造出第一台工业用机器人……科技的蓬勃发展也催生出主流科幻小说对人类创造力的无比自信，克拉克、阿西莫夫和海因莱因撑起了西方科幻的黄金时代。可是，迪克却反其道而行之，他的主人公迷惘于亦真亦假的世界上，挣扎于文明的陷落中，充满了对生命的依恋和对人性的追求。
         《仿生人会梦见电子羊吗？》是菲利普•迪克最负盛名的作品，小说描述了从一天早晨到第二天早晨的二十多个小时，主人公里克•德卡德为了赏金追杀几个仿生人的过程，历经无数变故，狗血共桃花一色，阴谋与暴力齐飞。小说里的人类受到地球辐射尘的影响，相貌丑陋，很多在心智上都发生了退化，而仿生人却外表堂堂，多才多艺。当仿生人在外表和心智上无限逼近人类，甚至表面上已经超越人类时，人究竟何以为人？
         1982年，菲利普•迪克去世后不久，根据《仿生人会梦见电子羊吗？》改编的电影《银翼杀手》上映，雷德利•斯科特导演。电影笼罩着一种迷离、朦胧、似幻似真的基调，描绘了一个末世大都市洛杉矶，是后世无数赛博朋克电影的鼻祖之作。我是谁？我从哪里来？要到哪里去？对这个哲学终极命题的探讨，让这部电影成为科幻电影史上的经典之作，也赋予其历久弥新的魅力。
         2017年，电影续集《银翼杀手2049》在北美定档10月6日，也即将与中国观众见面。关于生存和生命的意义，我们永远没有确切答案。
         核战后，放射尘让地球上的动物濒临灭绝，地球已不再适合人类居住。为了鼓励残存的人口移民，政府承诺，只要移民到外星球，就为每人自动配备一个仿生人帮助其生活。仿生人不满足于被人类奴役的现状，想方设法逃回地球。
         小说主人公里克•德卡德是一名专门追捕逃亡仿生人的赏金猎人。在一次追捕行动中，里克遭遇了新型仿生人前所未有的挑战。九死之后，能否一生？在与仿生人的接触和较量中，里克发现自己对仿生人的看法和态度有了很大的改变。这种改变究竟是福还是祸？
         * ebook_price : 20.00
         * series : {"id":"38198","title":"菲利普·迪克作品（精装）"}
         * price : 39.80
         */

        private RatingBean rating;
        private String subtitle;
        private String pubdate;
        private String origin_title;
        private String image;
        private String binding;
        private String catalog;
        private String ebook_url;
        private String pages;
        private ImagesBean images;
        private String alt;
        private String id;
        private String publisher;
        private String isbn10;
        private String isbn13;
        private String title;
        private String url;
        private String alt_title;
        private String author_intro;
        private String summary;
        private String ebook_price;
        private SeriesBean series;
        private String price;
        private List<String> author;
        private List<TagsBean> tags;
        private List<String> translator;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public String getOrigin_title() {
            return origin_title;
        }

        public void setOrigin_title(String origin_title) {
            this.origin_title = origin_title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBinding() {
            return binding;
        }

        public void setBinding(String binding) {
            this.binding = binding;
        }

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public String getEbook_url() {
            return ebook_url;
        }

        public void setEbook_url(String ebook_url) {
            this.ebook_url = ebook_url;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getIsbn10() {
            return isbn10;
        }

        public void setIsbn10(String isbn10) {
            this.isbn10 = isbn10;
        }

        public String getIsbn13() {
            return isbn13;
        }

        public void setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getAuthor_intro() {
            return author_intro;
        }

        public void setAuthor_intro(String author_intro) {
            this.author_intro = author_intro;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getEbook_price() {
            return ebook_price;
        }

        public void setEbook_price(String ebook_price) {
            this.ebook_price = ebook_price;
        }

        public SeriesBean getSeries() {
            return series;
        }

        public void setSeries(SeriesBean series) {
            this.series = series;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<String> getAuthor() {
            return author;
        }

        public void setAuthor(List<String> author) {
            this.author = author;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<String> getTranslator() {
            return translator;
        }

        public void setTranslator(List<String> translator) {
            this.translator = translator;
        }

        public static class RatingBean {
            /**
             * max : 10
             * numRaters : 734
             * average : 8.7
             * min : 0
             */

            private int max;
            private int numRaters;
            private String average;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getNumRaters() {
                return numRaters;
            }

            public void setNumRaters(int numRaters) {
                this.numRaters = numRaters;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/spic/s29578434.jpg
             * large : https://img3.doubanio.com/lpic/s29578434.jpg
             * medium : https://img3.doubanio.com/mpic/s29578434.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class SeriesBean {
            /**
             * id : 38198
             * title : 菲利普·迪克作品（精装）
             */

            private String id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class TagsBean {
            /**
             * count : 397
             * name : 科幻
             * title : 科幻
             */

            private int count;
            private String name;
            private String title;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
