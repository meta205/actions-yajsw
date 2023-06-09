/*******************************************************************************
 * Copyright  2015 rzorzorzo@users.sf.net
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
 *******************************************************************************/

package org.rzo.yajsw.os;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractProcess.
 */
public abstract class AbstractProcess implements Process
{

	/** The _cmd. */
	protected String _cmd;

	protected String[] _arrCmd;

	/** The _working dir. */
	protected String _workingDir;

	/** The _priority. */
	protected int _priority = PRIORITY_UNDEFINED;
	protected String _nice = null;

	/** The _visible. */
	protected boolean _visible = true;

	/** The _pid. */
	volatile protected int _pid = -1;

	/** The _exit code. */
	volatile protected int _exitCode = -1;
	volatile protected int _exitSignal = -1;

	/** The _title. */
	protected String _title = "";

	/** The _cpu affinity. */
	protected long _cpuAffinity = AFFINITY_UNDEFINED;

	/** The _cpu affinity. */
	protected String _cpuAffinityBitset = null;

	/** The _pipe streams. */
	protected boolean _pipeStreams = false;

	/** The _redirect error stream. */
	protected boolean _redirectErrorStream = false;

	/** The _input stream. */
	protected InputStream _inputStream;

	/** The _output stream. */
	protected OutputStream _outputStream;

	/** The _error stream. */
	protected InputStream _errorStream;

	/** The in_fd. */
	protected final FileDescriptor in_fd = new FileDescriptor();

	/** The out_fd. */
	protected final FileDescriptor out_fd = new FileDescriptor();

	/** The err_fd. */
	protected final FileDescriptor err_fd = new FileDescriptor();

	/** The _tee name. */
	protected String _teeName;

	/** The _tmp path. */
	protected String _tmpPath;

	protected String _user;
	protected String _password;
	protected Logger _logger;
	protected List<String[]> _environment = new ArrayList<String[]>();
	protected boolean _debug = false;
	protected boolean _minimized = false;
	protected boolean _logonActiveSession = false;
	protected String _desktop = null;
	protected int _umask = -1;
	protected boolean _useSpawn = false;
	protected boolean _linuxUseVfork = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getCommand()
	 */
	public String getCommand()
	{
		return _cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#setCommand(java.lang.String)
	 */
	public void setCommand(String cmd)
	{
		_cmd = cmd;
	}

	/**
	 * Sets the command.
	 * 
	 * @param cmds
	 *            the new command
	 */
	public void setCommand(String[] cmds)
	{
		_arrCmd = cmds;
		_cmd = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getEnv()
	 */
	public List<String[]> getEnvironment()
	{
		return _environment;
	}

	public Map<String, String> getEnvironmentAsMap()
	{
		Map result = new HashMap();
		for (String[] entry : _environment)
			result.put(entry[0], entry[1]);
		return result;
	}

	/**
	 * Sets the env.
	 * 
	 * @param env
	 *            the new env
	 */
	public void setEnvironment(List<String[]> environment)
	{
		if (environment == null)
			environment = new ArrayList<String[]>();
		_environment = environment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getExitCode()
	 */
	public int getExitCode()
	{
		return _exitCode;
	}

	/**
	 * Sets the exit code.
	 * 
	 * @param exitCode
	 *            the new exit code
	 */
	protected void setExitSignal(int exitSignal)
	{
		_exitSignal = exitSignal;
	}

	public int getExitSignal()
	{
		return _exitSignal;
	}
	protected void setExitCode(int exitCode)
	{
		_exitCode = exitCode;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getPid()
	 */
	public int getPid()
	{
		return _pid;
	}

	/**
	 * Sets the pid.
	 * 
	 * @param pid
	 *            the new pid
	 */
	public void setPid(int pid)
	{
		_pid = pid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getPriority()
	 */
	public int getPriority()
	{
		return _priority;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#setPriority(int)
	 */
	public void setPriority(int priority)
	{
		_priority = priority;
	}

	public void setNice(String value)
	{
		_nice = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#isVisible()
	 */
	public boolean isVisible()
	{
		return _visible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#setVisible(boolean)
	 */
	public void setVisible(boolean visible)
	{
		_visible = visible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getWorkingDir()
	 */
	public String getWorkingDir()
	{
		return _workingDir;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#setWorkingDir(java.lang.String)
	 */
	public void setWorkingDir(String workingDir)
	{
		_workingDir = workingDir;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle()
	{
		return _title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#setTitle(java.lang.String)
	 */
	public void setTitle(String title)
	{
		_title = title;
	}

	/**
	 * Gets the cpu affinity.
	 * 
	 * @return the cpu affinity
	 */
	public long getCpuAffinity()
	{
		return _cpuAffinity;
	}

	public String getCpuAffinityBitset()
	{
		return _cpuAffinityBitset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#setCpuAffinity(int)
	 */
	public void setCpuAffinity(long cpuAffinity)
	{
		_cpuAffinity = cpuAffinity;
	}

	public void setCpuAffinityBitset(String value)
	{
		_cpuAffinityBitset = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#setPipeStreams(boolean, boolean)
	 */
	public void setPipeStreams(boolean pipeStreams, boolean redirectErrorStream)
	{
		_pipeStreams = pipeStreams;
		_redirectErrorStream = redirectErrorStream;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getInputStream()
	 */
	public InputStream getInputStream()
	{
		return _inputStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getErrorStream()
	 */
	public InputStream getErrorStream()
	{
		return _errorStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rzo.yajsw.os.Process#getOutputStream()
	 */
	public OutputStream getOutputStream()
	{
		return _outputStream;
	}

	/**
	 * Gets the tee name.
	 * 
	 * @return the tee name
	 */
	String getTeeName()
	{
		return _teeName;
	}

	/**
	 * Sets the tee name.
	 * 
	 * @param teeName
	 *            the new tee name
	 */
	public void setTeeName(String teeName)
	{
		_teeName = teeName;
	}

	/**
	 * Sets the tmp path.
	 * 
	 * @param tmpPath
	 *            the new tmp path
	 */
	public void setTmpPath(String tmpPath)
	{
		_tmpPath = tmpPath;
	}

	public String getUser()
	{
		return _user;
	}

	public void setUser(String user)
	{
		_user = user;
	}

	public String getPassword()
	{
		return _password;
	}

	public void setPassword(String password)
	{
		_password = password;
	}

	public void setLogger(Logger logger)
	{
		_logger = logger;
	}

	protected void log(String msg)
	{
		if (_logger != null)
			_logger.info(msg);
		else
			System.out.println(msg);
	}

	protected void log(String msg, Throwable ex)
	{
		if (_logger != null)
			_logger.log(Level.INFO, msg, ex);
		else
		{
			System.out.println(msg);
			ex.printStackTrace();
		}
	}

	public void setDebug(boolean debug)
	{
		_debug = debug;
	}

	public boolean isMinimized()
	{
		return _minimized;
	}

	public void setMinimized(boolean minimized)
	{
		_minimized = minimized;
	}

	public boolean isLogonActiveSession()
	{
		return _logonActiveSession;
	}

	public void setLogonActiveSession(boolean logonActiveSession)
	{
		_logonActiveSession = logonActiveSession;
	}

	public void setDesktop(String desktop)
	{
		_desktop = desktop;
	}

	public boolean isDebug()
	{
		return _debug;
	}

	public int getUmask()
	{
		return _umask;
	}

	public void setUmask(int umask)
	{
		_umask = umask;
	}

	public boolean isUseSpawn()
	{
		return _useSpawn;
	}

	public void setUseSpawn(boolean useSpawn)
	{
		_useSpawn = useSpawn;
	}

	public boolean isLinuxUseVfork()
	{
		return _linuxUseVfork;
	}

	public void setLinuxUseVfork(boolean linuxUseVfork)
	{
		_linuxUseVfork = linuxUseVfork;
	}

}
