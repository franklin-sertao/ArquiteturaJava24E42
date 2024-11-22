package br.edu.infnet.franklin.model.domain;

import java.util.List;

public class Cosmos {
	private String description;
	private long gtin;
	private String thumbnail;
	private Double width;
	private Double height;
	private Double length;
	private Double net_weight;
	private Double gross_weight;
	private String created_at;
	private String updated_at;
	private String release_date;
	private String price;
	private Double avg_price;
	private Double max_price;
	private Double min_price;
	private List<Gtin> gtins;
	private String origin;
	private String barcode_image;
	private Brand brand;
	private Ncm ncm;

	// Getters and Setters
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getGtin() {
		return gtin;
	}

	public void setGtin(long gtin) {
		this.gtin = gtin;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getNet_weight() {
		return net_weight;
	}

	public void setNet_weight(Double net_weight) {
		this.net_weight = net_weight;
	}

	public Double getGross_weight() {
		return gross_weight;
	}

	public void setGross_weight(Double gross_weight) {
		this.gross_weight = gross_weight;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Double getAvg_price() {
		return avg_price;
	}

	public void setAvg_price(Double avg_price) {
		this.avg_price = avg_price;
	}

	public Double getMax_price() {
		return max_price;
	}

	public void setMax_price(Double max_price) {
		this.max_price = max_price;
	}

	public Double getMin_price() {
		return min_price;
	}

	public void setMin_price(Double min_price) {
		this.min_price = min_price;
	}

	public List<Gtin> getGtins() {
		return gtins;
	}

	public void setGtins(List<Gtin> gtins) {
		this.gtins = gtins;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getBarcode_image() {
		return barcode_image;
	}

	public void setBarcode_image(String barcode_image) {
		this.barcode_image = barcode_image;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Ncm getNcm() {
		return ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public static class Gtin {
		private long gtin;
		private CommercialUnit commercial_unit;

		// Getters and Setters
		public long getGtin() {
			return gtin;
		}

		public void setGtin(long gtin) {
			this.gtin = gtin;
		}

		public CommercialUnit getCommercial_unit() {
			return commercial_unit;
		}

		public void setCommercial_unit(CommercialUnit commercial_unit) {
			this.commercial_unit = commercial_unit;
		}
	}

	public static class CommercialUnit {
		private String type_packaging;
		private int quantity_packaging;
		private Integer ballast;
		private Integer layer;

		// Getters and Setters
		public String getType_packaging() {
			return type_packaging;
		}

		public void setType_packaging(String type_packaging) {
			this.type_packaging = type_packaging;
		}

		public int getQuantity_packaging() {
			return quantity_packaging;
		}

		public void setQuantity_packaging(int quantity_packaging) {
			this.quantity_packaging = quantity_packaging;
		}

		public Integer getBallast() {
			return ballast;
		}

		public void setBallast(Integer ballast) {
			this.ballast = ballast;
		}

		public Integer getLayer() {
			return layer;
		}

		public void setLayer(Integer layer) {
			this.layer = layer;
		}
	}

	public static class Brand {
		private String name;
		private String picture;

		// Getters and Setters
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPicture() {
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
		}
	}

	public static class Ncm {
		private String code;
		private String description;
		private String full_description;
		private String ex;

		// Getters and Setters
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getFull_description() {
			return full_description;
		}

		public void setFull_description(String full_description) {
			this.full_description = full_description;
		}

		public String getEx() {
			return ex;
		}

		public void setEx(String ex) {
			this.ex = ex;
		}
	}
}
