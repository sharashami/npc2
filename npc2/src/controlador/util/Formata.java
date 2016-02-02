package controlador.util;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;


public class Formata {

	public static boolean validaCPF_CNPJ(String s_aux) {

		//------- Rotina para CPF
		if (s_aux.length() == 11) {
			int d1, d2;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;
			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;
			for (int n_Count = 1; n_Count < s_aux.length() - 1; n_Count++) {
				digitoCPF = Integer.valueOf(s_aux.substring(n_Count - 1, n_Count)).intValue();
				//--------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
				d1 = d1 + (11 - n_Count) * digitoCPF;
				//--------- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
				d2 = d2 + (12 - n_Count) * digitoCPF;
			}
			;
			//--------- Primeiro resto da divisão por 11.
			resto = (d1 % 11);
			//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
			if (resto < 2) {
				digito1 = 0;
			} else {
				digito1 = 11 - resto;
			}
			d2 += 2 * digito1;
			//--------- Segundo resto da divisão por 11.
			resto = (d2 % 11);
			//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
			if (resto < 2) {
				digito2 = 0;
			} else {
				digito2 = 11 - resto;
			}
			//--------- Digito verificador do CPF que está sendo validado.
			String nDigVerific = s_aux.substring(s_aux.length() - 2, s_aux.length());
			//--------- Concatenando o primeiro resto com o segundo.
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
			//--------- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
			return nDigVerific.equals(nDigResult);
		} //-------- Rotina para CNPJ
		else if (s_aux.length() == 14) {
			char[] chr_cnpj = s_aux.toCharArray();
			int peso=2, soma=0;
			// testa se a sequencia eh igual
	
			for (int i = 11; i >=0; i--) {
				soma +=peso*(chr_cnpj[i]-48);
				peso=peso+1;
				if(peso==10) peso=2;
			}
			
			if(soma%11==0 || soma%11==1){
				
				return ((chr_cnpj[12]-48)==0)? true:false;
			}else{
			
				if((11-(soma%11))==(chr_cnpj[12]-48)){
					// verifica o segundo caso
					
					soma=0;
					peso=2;
	
					for (int i = 12; i >=0; i--) {
						soma +=peso*(chr_cnpj[i]-48);
						peso=peso+1;
						if(peso==10) peso=2;
					}
				
					if(soma%11==0 || soma%11==1){
						return ((chr_cnpj[13]-48)==0)? true:false;
					}else{
						return ((11-(soma%11))==(chr_cnpj[13]-48))?true:false;
					}
					
				}else{
					return false;
				}
			}
		} else {
			return false;
		}
	}

	public enum Padrao {

		MOEDA(",##0.00"), QUANTIDADE(",##0.00#"), VALORB(",##0.###"),VALOR_PONTUACAO(",##0.#"),
      CNPJ("00000000000000"), CPF("00000000000"), _4_DIGIT("0000");

		private Padrao(String value) {
			this.value = value;
		}
		private final String value;

		public String value() {
			return value;
		}
	}
	public static final String getTagPlayVideoFlv(String enderecoVideo) {
		StringBuilder estruturavideo = new StringBuilder();
		estruturavideo.append("<div style=\"position:relative;height:250px;width:250px;\" class=\"flowplayer\" id=\"player\" data-swf=\"resources/flowplayer/flowplayer.swf\" data-ratio=\"0.4167\">");
		estruturavideo.append("<video><source type=\"video/x-flv\" src=\""+enderecoVideo+"\"></video>");
		estruturavideo.append("</div>");
		return estruturavideo.toString();
	}
	/**
	 * 
	 * @param urlDoRed5 EX.: rtmp://177.70.23.42/ead2pcd_red5
	 * @param nomeParaVideo EX.: localhost/streams/Sinal_Topico1_14374
	 * @return 
	 */
	public static final String getGravadorDeStriming(String urlDoRed5,String nomeParaVideo ,int width,int heigth ) {
		StringBuilder estruturavideo = new StringBuilder();
		estruturavideo.append("<object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\"");
		estruturavideo.append("width=\""+width+"\" height=\""+heigth+"\" id=\"simpleRecorder\">");
		estruturavideo.append("<param name=\"movie\"");
		estruturavideo.append("value=\"/resources/pluginUploadVideo/simpleRecorder.swf\" />");
		estruturavideo.append("<param name=\"quality\" value=\"high\" />");
		estruturavideo.append("<param name=\"bgcolor\" value=\"#ffffff\" />");
		estruturavideo.append("<param name=\"play\" value=\"true\" />");
		estruturavideo.append("<param name=\"loop\" value=\"true\" />");
		estruturavideo.append("<param name=\"wmode\" value=\"opaque\" />");
		estruturavideo.append("<param name=\"scale\" value=\"showall\" />");
		estruturavideo.append("<param name=\"menu\" value=\"true\" />");
		estruturavideo.append("<param name=\"devicefont\" value=\"false\" />");
		estruturavideo.append("<param name=\"salign\" value=\"\" />");
		estruturavideo.append("<param name=\"allowScriptAccess\" value=\"sameDomain\" />");
		estruturavideo.append("<param name=\"FlashVars\"");
		estruturavideo.append("value=\"FileName=");
		estruturavideo.append(nomeParaVideo);
		estruturavideo.append("&Path=");
		estruturavideo.append(urlDoRed5);
		estruturavideo.append("\" />");
		estruturavideo.append("<!--[if !IE]>-->");
		estruturavideo.append("<object type=\"application/x-shockwave-flash\"");
		estruturavideo.append("data=\"resources/pluginUploadVideo/simpleRecorder.swf\"");
		estruturavideo.append("width=\""+width+"\" height=\""+heigth+"\" >");
		estruturavideo.append("<param name=\"movie\"");
		estruturavideo.append("value=\"/resources/pluginUploadVideo/simpleRecorder.swf\" />");
		estruturavideo.append("<param name=\"quality\" value=\"high\" />");
		estruturavideo.append("<param name=\"bgcolor\" value=\"#ffffff\" />");
		estruturavideo.append("<param name=\"play\" value=\"true\" />");
		estruturavideo.append("<param name=\"loop\" value=\"true\" />");
		estruturavideo.append("<param name=\"wmode\" value=\"opaque\" />");
		estruturavideo.append("<param name=\"scale\" value=\"showall\" />");
		estruturavideo.append("<param name=\"menu\" value=\"true\" />");
		estruturavideo.append("<param name=\"devicefont\" value=\"false\" />");
		estruturavideo.append("<param name=\"salign\" value=\"\" />");
		estruturavideo.append("<param name=\"allowScriptAccess\" value=\"sameDomain\" />");
		estruturavideo.append("<param name=\"FlashVars\"");
		estruturavideo.append("	value=\"FileName=");
		estruturavideo.append(nomeParaVideo);
		estruturavideo.append("&Path=");
		estruturavideo.append(urlDoRed5);
		estruturavideo.append("\" />");
			estruturavideo.append("<!--<![endif]-->");
		estruturavideo.append("<a href=\"http://www.adobe.com/go/getflash\"> <img ");
			estruturavideo.append("	src=\"http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif\"");
				estruturavideo.append("	alt=\"Obter Adobe Flash Player\" />");
		estruturavideo.append("</a>");
		estruturavideo.append("<!--[if !IE]>-->");
		estruturavideo.append("</object>");
		estruturavideo.append("<!--<![endif]-->");
		estruturavideo.append("</object>");
		return estruturavideo.toString();
	}
	public static final boolean isURLFile(String urlString,boolean isArquivo) {
		boolean existe=false;
		//String urlString ="http://177.70.23.42/streams/ajads_video_duvida_1392058142543.fvl";//198204545
		if(!isArquivo){
			try {
				java.net.URL url = new java.net.URL(urlString);
				java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
				connection.setDoInput(true);  
				connection.setDoOutput(false);
				connection.connect();
				java.io.BufferedReader br =  
					    new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream())); 
				existe=true;
			} catch (java.net.MalformedURLException e) {
				existe=false;
				e.printStackTrace();
			} catch (java.io.IOException e) {
				existe=false;
				e.printStackTrace();
			}
		}else{
			System.out.println(urlString+"+++++++++++++++++++++++++++++++");
			boolean exists = (new java.io.File(urlString)).exists();  
		    if (exists) {  
		    	existe=true;
		    } else {
		    	existe=false;
		    } 
		}
		return existe;
	}
	public static final String toUTF8(String str) {
		String utf8 = null;
		if (str != null && !str.equals("")) {
			      try {
            byte[] iso = str.getBytes("ISO-8859-1");//ISO-8859-1
            utf8 = new String(iso, "UTF-8");//UTF-8
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
		} else {
			utf8 = str;
		}
		return utf8;
	}
	private static final NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));   // NumberFormat.getInstance(Locale.getDefault());

	public static String formatarValores(Double valor, Padrao padrao) {
		return new DecimalFormat(padrao.value()).format(valor);
	}
	public static String formatarValores(Float valor, Padrao padrao) {
		return new DecimalFormat(padrao.value()).format(valor);
	}
	

	public static String getDoubleFormatLocal(double valor) {
		try {
			if (valor == 0) {
				return "0,00";
			} else {
				return formato.format(valor);
			}
		} catch (Exception e) {
			return null;
		}
	}
	public static String trimToEmpty(String str) {
		return StringUtils.trimToEmpty(str);
	}
	public static Double getPorcentoDoValor(Double valor, Double porcentagem) {
		if (valor != null && porcentagem != null) {
			return (valor * porcentagem / 100) + valor;
		}
		return 0.0;
	}

	public static Double convDaStringDouble(String valor) {
		DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();
		if (valor != null) {
			try {
				return dff.parse(valor).doubleValue();
			} catch (ParseException e1) {
				return 0.0;
			}
		}
		
		/*
		if(valor!=null)
		try {

		return formato.parse ("R$ "+valor).doubleValue();

		} catch (ParseException e) {
		System.out.print(valor+"EEEEEEE");
		return 0.0;
		}
		return 0.0;
		 */
		return 0.0;
	}
	public static float convDaStringFloat(String valor) {
		DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();
		if (valor != null) {
			try {
				return dff.parse(valor).floatValue();
			} catch (ParseException e1) {
				return 0f;
			}
		}
		return 0f;
	}
	public static Integer getInteger(String valor) {

		try {
			if (valor != null && !valor.isEmpty()) {
				return Integer.valueOf(valor.replaceAll("[\\D]", "").trim());
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static int parseInt(String valor) {
		try {
			if (valor != null && !valor.isEmpty()) {
				return Integer.valueOf(valor.replaceAll("[\\D]", "").trim());
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			
			return 0;
		}
	}
	public static int parseInt(Integer valor) {
		try {
			if (valor != null) {
				return Integer.valueOf(valor);
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			
			return 0;
		}
	}
	public static long parseLong(String valor) {
		try {
			if (valor != null && !valor.isEmpty()) {
				return Long.valueOf(valor.replaceAll("[\\D]", "").trim());
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}	
	public static long parseLong(Long valor) {
		try {
			if (valor != null) {
				return Long.valueOf(valor);
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static float parseFloat(String valor) {
		try {
			if (valor != null && !valor.isEmpty()) {
				return Float.valueOf(valor);
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static Object getCampoView(Object valor) {

		if (valor == null) {
			return "";
		}
		return valor;
	}

	public static Long getLong(String valor) {
		try {
			if (valor != null && !valor.isEmpty()) {
				return new Long(valor.replaceAll("[\\D]", "").trim());
			}
			return null;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public double testRound(double valor, int decimalPlace) {

		BigDecimal bd = new BigDecimal(valor);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	public static Double getDoubleFormatPadrao(String valorDouble) {
		if (valorDouble != null) {
			try {
				return formato.parse("R$ " + valorDouble).doubleValue();
			} catch (ParseException e) {
			}
		}
		return 0.0;
	}

	public static String getDecimalDaString(String valor) {

		if (valor != null) {
			return valor.replaceAll("[\\D]", "").trim();
		}
		return null;
	}

	public static BigDecimal getbigDecimalDaString(String valor) {

		if (valor != null && !valor.isEmpty()) {
			return new BigDecimal(valor.replaceAll("[\\D]", "").trim());
		}
		return null;
	}

	public static int convetNmeroInt(String valor) {
		try {
			return Integer.parseInt(valor.replaceAll("[\\D]", ""));
		} catch (Exception e) {
			return 0;
		}
	}

	public static long convetNmeroLon(String valor) {
		try {
			return Long.parseLong(valor.replaceAll("[\\D]", ""));
		} catch (Exception e) {
			return 0;
		}
	}

	public static String formatDatadd_mm_aaaaa(Timestamp data) {
		try {
			String datd = "";
			if (data != null) {
				datd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
			}
			return datd;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String formatDatadd_mm_aaaaa(java.util.Date data) {
		try {
			String datd = "";
			if (data != null) {
				datd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
			}
			return datd;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String getString(String nome) {
		if (nome != null && !nome.isEmpty()) {
			return nome.trim();
		} else {
			return null;
		}
	}

	public static String getStringView(String nome) {
		if (nome != null && !nome.isEmpty()) {
			return nome.trim();
		} else {
			return "";
		}
	}

	/**
	 * @author alberto
	 * @param tipo se 0 o formato que deve ser passado sera dd/MM/yyyy hh:mm:ss se 1 yyyy/MM/dd hh:mm:ss
	 * @return se houver erros retornarar null
	 */
	public static Timestamp getTimeStamp(int tipo, String data) {
		try {
			if (data != null && !data.isEmpty()) {
				SimpleDateFormat sdf = (tipo == 0) ? new SimpleDateFormat("dd/MM/yyyy hh:mm:ss") : new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				return new Timestamp(sdf.parse(data).getTime());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatDatadd_mm_aaaaa(Date data) {
		try {
			String datd = "";
			if (data != null) {
				datd = new SimpleDateFormat("dd/MM/yyyy").format(data);
			}
			return datd;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static java.sql.Date formatDatadd_mm_aaaaa(String data) {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if (data != null) {
			try {
				return new java.sql.Date(format.parse(data.trim()).getTime());
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static String getDataAtual() {
		java.text.DateFormat df2 = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, new Locale("pt", "BR"));
		return df2.format(new java.util.Date());
	}
	
	/**Retira casas decimais de acordo com o padrao P
	 * 
	 * @param valor
	 * @param pplayer
	 * @return
	 */
	public  static Double convDoubleDouble(double valor,Padrao p){
		return Formata.convDaStringDouble(Formata.formatarValores(valor, p));
	}
   
   public static String limitarCaracteres(String s, int start, int limit) {
      String aux = new String();
      if (StringUtils.isNotBlank(s)) {
         aux = StringUtils.substring(s, start, limit);
      }
      return aux;
   }
   public static String getPlayVideo(String endENomeVideo, String id,String descricao){
	   return "<a href=\""+endENomeVideo+"\" id-video=\""+id+"\" class=\"msg_video\" id=\"player"+id+"\"> </a> <script language=\"JavaScript\"> flowplayer(\"player"+id+"\", \"resources/flowplayer/flowplayer-3.2.18.swf\",{plugins: {},clip: {autoPlay: false}}); </script><span style=\"  color: #d8d8d8;font-size: 1.5em; height: 100%;\">"+Formata.trimToEmpty(descricao)+" </span>";

   }
}
