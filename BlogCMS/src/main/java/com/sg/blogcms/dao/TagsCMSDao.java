/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.Tags;
import java.util.List;

/**
 *
 * @author svlln
 */
public interface TagsCMSDao {
    Tags createTag(Tags tag);
    void removeTag(int tagID);
    Tags updateTag(Tags tag);
    Tags SelectTag(int tagID);
    List<Tags> SelectAllTags();
    
}
