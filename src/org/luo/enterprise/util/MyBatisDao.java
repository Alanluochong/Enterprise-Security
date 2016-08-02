package org.luo.enterprise.util;

import org.springframework.stereotype.Repository;

@Repository
public @interface MyBatisDao {
	String value() default "";
}
