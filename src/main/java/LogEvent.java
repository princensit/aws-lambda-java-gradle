/*
 * Copyright 2021 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogEvent {
  private String id;
  private long timestamp;
  private String message;
}
