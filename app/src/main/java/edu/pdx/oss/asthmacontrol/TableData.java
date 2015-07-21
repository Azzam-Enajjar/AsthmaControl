package edu.pdx.oss.asthmacontrol;

import android.provider.BaseColumns;

public class TableData {

    public static abstract class TableInfo implements BaseColumns{
        public static final String DATABASE_NAME = "asthmaDB";

        public static final String USER_TABLE = "userTable";
        public static final String USER_NAME = "userName";
        public static final String USER_PASSWORD = "userPassword";

        public static final String ASTHMA_TIME_TABLE = "asthmaTime";
        public static final String ASTHMA_TIME_DATE ="logDate";

        public static final String ASTHMA_BREATH_TABLE = "asthmaBreath";
        public static final String ASTHMA_BREATH_DATE ="logDate";

        public static final String ASTHMA_SYMPTOMS_TABLE = "asthmaSymptoms";
        public static final String ASTHMA_SYMPTOMS_DATE ="logDate";

        public static final String ASTHMA_MEDICATION_TABLE = "asthmaMedication";
        public static final String ASTHMA_MEDICATION_DATE ="logDate";
    }
}
