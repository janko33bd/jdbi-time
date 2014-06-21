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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.temporal.TemporalAccessor;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;

public class TemporalAsStringArgument<T extends TemporalAccessor> implements Argument {

  public static <T extends TemporalAccessor> Argument of(T type) {
    return new TemporalAsStringArgument<>(type);
  }

  private final T value;

  public TemporalAsStringArgument(T value) {
    this.value = value;
  }

  public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {
    if (value == null) {
      statement.setNull(position, Types.VARCHAR);
    } else {
      statement.setString(position, value.toString());
    }
  }
}
