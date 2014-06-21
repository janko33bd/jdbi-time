/*
 * Copyright 2014 Rob Fletcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.freeside.jdbi.time;

import java.time.*;
import java.util.HashMap;
import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.ResultSetMapperFactory;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Provides mappers for all supported +java.time+ types.
 *
 * @see DBI#registerMapper(ResultSetMapperFactory)
 * @see Handle#registerMapper(ResultSetMapperFactory)
 */
public class TimeTypesMapperFactory implements ResultSetMapperFactory {

  private static final Map<Class, ResultSetMapper> mappers = new HashMap<>();

  static {
    mappers.put(Instant.class, new InstantMapper());
    mappers.put(LocalDate.class, new LocalDateMapper());
    mappers.put(LocalDateTime.class, new LocalDateTimeMapper());
    mappers.put(LocalTime.class, new LocalTimeMapper());
    mappers.put(MonthDay.class, new MonthDayMapper());
    mappers.put(YearMonth.class, new YearMonthMapper());
  }

  public boolean accepts(Class type, StatementContext ctx) {
    return mappers.containsKey(type);
  }

  public ResultSetMapper mapperFor(Class type, StatementContext ctx) {
    return mappers.get(type);
  }
}