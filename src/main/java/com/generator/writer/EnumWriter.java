package com.generator.writer;

import com.generator.model.EnumModel;

public interface EnumWriter extends Writer{
	   void create(EnumModel enumModel) throws Exception;
}
