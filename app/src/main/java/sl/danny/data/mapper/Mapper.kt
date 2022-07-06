package sl.danny.data.mapper

import org.koin.core.annotation.Single
import sl.danny.bean.JokeBean
import sl.danny.data.entity.JokeEntity


@Single
class JokeMapper {

    fun fromBeanToEntity(bean: JokeBean) = JokeEntity(bean.content, bean.updateTime)


    fun fromEntityToBean(entity: JokeEntity) =
        JokeBean(entity.content ?: "", entity.updateTime ?: "")

}