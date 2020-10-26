package bit.project.restfull.database;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)//��Ĺ ���� �Ƚ��ѵ� ��Ĺ ������ �� ���� ȯ���� �������
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//�� ������ġ���� context���ϸ� �о�鿩�� IOC�����̳ʸ� ������ֹǷ�, �������� �׽�Ʈ�� ��������
@Log4j
public class DataSourceTest {
	//DataSource Ŭ������ �������� �������� �˰� ����Ʈ�� �ȵ���
	//^^.. ������ ������ �Ű澲��
	@Autowired
	DataSource dataSource;
		
	@Test
	public void testDataSource() {
		System.out.println(dataSource);
	}

}
