package com.qa.ims.controller;


 import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

public class ItemsController implements CrudController<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemsDAO itemDAO;
	private Utils utils;

	public ItemsController(ItemsDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> items = itemDAO.readAll();
		for (Items item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Items create() {
		LOGGER.info("Please enter a product name");
		String itemName = utils.getString();
		LOGGER.info("Please enter a price");
		double cost = utils.getDouble();
		Items item = itemDAO.create(new Items(itemName, cost));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Items update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter the item price");
		Double cost = utils.getDouble();
		Items item = itemDAO.update(new Items(id, itemName, cost));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}
