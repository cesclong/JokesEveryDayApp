package sl.danny.bean

data class JokeBean(
    val content: String,
    val updateTime: String
)

data class JokeResponseDataBean(
    val code: Int,
    val msg: String,
    val data : JokeDataBean
)


data class JokeDataBean(
    val list: List<JokeBean>,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int,
    val limit: Int
)
