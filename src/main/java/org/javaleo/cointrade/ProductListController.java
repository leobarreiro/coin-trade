package org.javaleo.cointrade;

import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "productListController")
@ELBeanName(value = "productListController")
@Join(path = "/", to = "/product-list.jsf")
public class ProductListController {

	@Autowired
	private ProductRepository productRepository;

	private List<Product> products;

	private OhlcChartModel ohlc;

	@PostConstruct
	public void loadChart() {
		ohlc = new OhlcChartModel();
		ohlc.setCandleStick(true);

		for (int i = 1; i < 41; i++) {
			ohlc.add(new OhlcChartSeries(i, Math.random() * 80 + 80, Math.random() * 50 + 110, Math.random() * 20 + 80,
					Math.random() * 80 + 80));
		}

		ohlc.setTitle("Candlestick");
		ohlc.setCandleStick(true);
		ohlc.getAxis(AxisType.X).setLabel("Sector");
		ohlc.getAxis(AxisType.Y).setLabel("Index Value");
	}

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData() {
		products = productRepository.findAll();
		products = productRepository.findByName("Gabro");
	}

	public List<Product> getProducts() {
		return products;
	}

	public OhlcChartModel getOhlc() {
		return ohlc;
	}

	public void setOhlc(OhlcChartModel ohlc) {
		this.ohlc = ohlc;
	}

}
