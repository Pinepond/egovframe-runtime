/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.egovframe.brte.sample.testcase.test;

import org.egovframe.brte.sample.common.domain.trade.CustomerCredit;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* DB 의 Table을 한 라인씩 읽어서 데이터처리를 수행하는 테스트
* @author 배치실행개발팀
* @since 2012. 06.27
* @version 1.0
* @see
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/org/egovframe/batch/jobs/jdbcCursorIoJob.xml")
public class EgovJdbcCursorFunctionalTests extends EgovAbstractIoSampleTests {

	private JdbcTemplate jdbcTemplate;

	/**
	 * datasource 설정
	 *
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 배치작업 테스트 전에 DB관련 작업
	 */
	@Before
	public void setUp() {
		jdbcTemplate.update("DELETE from CUSTOMER");

		jdbcTemplate.update("INSERT INTO CUSTOMER (ID, VERSION, NAME, CREDIT) VALUES  (1, 0, 'customer1', 100000)");
		jdbcTemplate.update("INSERT INTO CUSTOMER (ID, VERSION, NAME, CREDIT) VALUES  (2, 0, 'customer2', 100000)");
		jdbcTemplate.update("INSERT INTO CUSTOMER (ID, VERSION, NAME, CREDIT) VALUES  (3, 0, 'customer3', 100000)");
		jdbcTemplate.update("INSERT INTO CUSTOMER (ID, VERSION, NAME, CREDIT) VALUES  (4, 0, 'customer4', 100000)");

	}

	/**
	 * 배치결과를 다시 읽을 때  reader 설정하는 메소드
	 */
	@Override
	protected void pointReaderToOutput(ItemReader<CustomerCredit> reader) {
		// no-op
	}

}
